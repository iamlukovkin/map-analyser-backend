package com.geo.dto;

import lombok.Data;

@Data
public class FlatMapLayerDto {
    private Long layerId;
    private String layerName;

    private Long featureId;
    private String featureName;
    private String locatedName;

    private String h3Hex;
    private Integer year;
    private Double avgMeasurement;

    public FlatMapLayerDto(
            Long layerId,
            String layerName,
            Long featureId,
            String featureName,
            String locatedName,
            String h3Hex,
            Integer year,
            Double avgMeasurement
    ) {
        this.layerId = layerId;
        this.layerName = layerName;
        this.featureId = featureId;
        this.featureName = featureName;
        this.locatedName = locatedName;
        this.h3Hex = h3Hex;
        this.year = year;
        this.avgMeasurement = avgMeasurement;
    }
}
