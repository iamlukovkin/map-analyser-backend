package com.geo.util;

import com.geo.entity.Feature;
import com.geo.model.FeatureModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FeatureMapper implements Function<FeatureModel, Feature> {
    @Override
    public Feature apply(FeatureModel featureModel) {
        return Feature.builder()
                .fullName(featureModel.getFullName())
                .locatedName(featureModel.getLocatedName())
                .build();
    }
}
