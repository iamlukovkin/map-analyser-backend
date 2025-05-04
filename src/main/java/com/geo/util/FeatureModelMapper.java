package com.geo.util;

import com.geo.entity.Feature;
import com.geo.model.FeatureModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FeatureModelMapper implements Function<Feature, FeatureModel> {
    @Override
    public FeatureModel apply(Feature feature) {
        return FeatureModel.builder()
                .id(feature.getId())
                .fullName(feature.getFullName())
                .locatedName(feature.getLocatedName())
                .build();
    }
}
