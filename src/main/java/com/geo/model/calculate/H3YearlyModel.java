package com.geo.model.calculate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class H3YearlyModel {
    private int year;
    private double featureMeasurement;
    private Long featureId;
    private String h3;
}
