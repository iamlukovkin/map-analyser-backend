package com.geo.service;

import com.geo.entity.Feature;
import com.geo.model.calculate.H3YearlyModel;
import com.geo.repository.FeatureInH3Repository;
import com.geo.repository.FeatureRepository;
import com.geo.repository.GeoRegionRepository;
import com.geo.repository.H3Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapDetailService {
    private final FeatureRepository featureRepository;
    private final GeoRegionRepository geoRegionRepository;
    private final H3Repository h3Repository;
    private final FeatureInH3Repository featureInH3Repository;

    @Cacheable(value = "featureInH3ByLayer", key = "#layerId + '_' + #hexSize")
    public List<H3YearlyModel> getByLayerId(Long layerId, Long hexSize) {
        var features = featureRepository.findFeaturesByLayerId(layerId);
        var geoRegion = geoRegionRepository.findGeoRegionByFeatureLayerId(layerId);
        var hexes = h3Repository.findH3ByRegionIdAndFeatures(
                geoRegion.getId(),
                features.stream().map(Feature::getId).collect(Collectors.toList()),
                hexSize
        );
        return featureInH3Repository.findMatchedRelationsOf(hexes, features);
    }

}
