package com.geo.service;

import com.geo.entity.Feature;
import com.geo.entity.H3;
import com.geo.mapper.H3YearlyModelMapper;
import com.geo.mapper.LayerMapModelMapper;
import com.geo.model.FeatureMapModel;
import com.geo.model.H3YearlyModel;
import com.geo.model.LayerMapModel;
import com.geo.model.LayersAndAreasModel;
import com.geo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapDetailService {
    private final FeatureRepository featureRepository;
    private final GeoRegionRepository geoRegionRepository;
    private final H3Repository h3Repository;
    private final FeatureInH3Repository featureInH3Repository;
    private final FeatureLayerRepository featureLayerRepository;
    private final H3YearlyModelMapper mapper;

    public List<H3YearlyModel> getByLayerId(Long layerId, Long hexSize) {
        var features = featureRepository.findFeaturesByLayerId(layerId);
        var geoRegion = geoRegionRepository.findGeoRegionByFeatureLayerId(layerId);
        List<Long> featureIds = features.stream()
                .map(Feature::getId)
                .collect(Collectors.toList());
        var hexes = h3Repository.findH3ByRegionIdAndFeatures(
                geoRegion.getId(),
                featureIds,
                hexSize);
        return featureInH3Repository.findMatchedRelationsOf(hexes, featureIds);

    }

    public List<H3YearlyModel> findYearlyByProductAndHexSize(Long productId, Long hexSize) {
        return featureInH3Repository.findYearlyByProductAndHexSize(productId, hexSize);
    }

    public LayersAndAreasModel findLayersAndAreas(Long productId, Long hexSize) {
        // Получаем слои продукта
        List<LayerMapModel> layers = findLayersOfProduct(productId);

        // Список id всех слоев
        List<Long> layerIds = layers.stream()
                .map(LayerMapModel::getId)
                .toList();

        // Получаем список регионов по слоям (уникальные regionId)
        List<Long> regionIds = geoRegionRepository.findRegionsByLayerIds(layerIds);

        // Собираем уникальные id фич из всех слоев
        List<Long> features = layers.stream()
                .flatMap(layer -> layer.getFeatures().stream())
                .map(FeatureMapModel::getId)
                .distinct()
                .toList();

        // Получаем все H3 объекты по регионам, фичам и размеру гексагона
        List<H3> hexes = h3Repository.findH3ByLayerIdsAndHexSize(regionIds, features, hexSize);

        // Инициализация мапы и сеттер для mapper
        var map = new HashMap<String, HashMap<Long, HashMap<Integer, Double>>>();
        mapper.setSourceMap(map);

        // Заполняем map данными
        featureInH3Repository.findMatchedRelationsOf(hexes, features)
                .forEach(relation -> map.put(relation.getH3(), mapper.apply(relation)));

        return new LayersAndAreasModel(layers, map);
    }


    public List<LayerMapModel> findLayersOfProduct(@RequestParam Long productId) {
        var layers = featureLayerRepository.findByProductId(productId);
        return layers.stream()
                .map(new LayerMapModelMapper())
                .toList();
    }
}
