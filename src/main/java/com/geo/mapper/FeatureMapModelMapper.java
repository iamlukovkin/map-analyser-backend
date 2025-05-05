package com.geo.mapper;

import com.geo.entity.Feature;
import com.geo.model.FeatureMapModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FeatureMapModelMapper implements Function<Feature, FeatureMapModel> {
    @Override
    public FeatureMapModel apply(Feature feature) {
        return FeatureMapModel.builder()
                .id(feature.getId())
                .fullName(feature.getFullName())
                .locatedName(feature.getLocatedName())
                .build();
    }
}
