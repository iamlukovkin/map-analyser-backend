package com.geo.util;

import com.geo.entity.FeatureLayer;
import com.geo.model.FeatureLayerModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FeatureLayerModelMapper implements Function<FeatureLayer, FeatureLayerModel> {
    @Override
    public FeatureLayerModel apply(FeatureLayer featureLayer) {
        return FeatureLayerModel.builder()
                .fullName(featureLayer.getFullName())
                .regionId(featureLayer.getRegionId())
                .build();
    }
}
