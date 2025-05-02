package com.geo.util;

import com.geo.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MapDetailsMapper {

    public MapDetailsDto toMapDetailsDto(List<FlatMapLayerDto> flatList) {
        List<Integer> years = flatList.stream()
                .map(FlatMapLayerDto::getYear)
                .filter(Objects::nonNull)
                .distinct()
                .sorted()
                .toList();

        Map<Long, List<FlatMapLayerDto>> byLayer = flatList.stream()
                .collect(Collectors.groupingBy(FlatMapLayerDto::getLayerId));

        List<LayerDetailsDto> layerDetails = new ArrayList<>();
        List<LayerDataDto> layerData = new ArrayList<>();

        for (var layerEntry : byLayer.entrySet()) {
            Long layerId = layerEntry.getKey();
            String layerName = layerEntry.getValue().getFirst().getLayerName();

            Map<Long, List<FlatMapLayerDto>> featuresMap = layerEntry.getValue().stream()
                    .collect(Collectors.groupingBy(FlatMapLayerDto::getFeatureId));

            List<FeatureDetailsDto> featureDescriptions = new ArrayList<>();
            List<FeatureDataDto> featureData = new ArrayList<>();

            for (var featureEntry : featuresMap.entrySet()) {
                Long featureId = featureEntry.getKey();
                FlatMapLayerDto anyRow = featureEntry.getValue().getFirst();

                featureDescriptions.add(new FeatureDetailsDto(
                        featureId,
                        anyRow.getFeatureName(),
                        anyRow.getLocatedName()
                ));

                List<H3DataDto> h3s = featureEntry.getValue().stream()
                        .map(r -> {
                            H3DataDto h3 = new H3DataDto();
                            h3.setH3(r.getH3Hex());
                            h3.setYear_of(r.getYear());
                            h3.setAverage_measurement(r.getAvgMeasurement());
                            return h3;
                        }).toList();

                featureData.add(new FeatureDataDto(featureId, h3s));
            }

            layerDetails.add(new LayerDetailsDto(layerId, layerName, featureDescriptions));
            layerData.add(new LayerDataDto(layerId, featureData));
        }

        return MapDetailsDto.builder()
                .orderedYears(years)
                .layers(layerDetails)
                .layersData(layerData)
                .build();
    }
}
