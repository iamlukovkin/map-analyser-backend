package com.geo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class H3YearlyModel {

    @JsonProperty("year")
    private int year;

    @JsonProperty("feature_measurement")
    private double featureMeasurement;

    @JsonProperty("feature_id")
    private Long featureId;

    @JsonProperty("h3")
    private String h3;


}
