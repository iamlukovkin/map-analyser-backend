package com.geo.util;

import com.geo.entity.FeatureLayer;
import com.geo.model.FeatureLayerModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FeatureLayerMapper implements Function<FeatureLayerModel, FeatureLayer> {
    @Override
    public FeatureLayer apply(FeatureLayerModel featureLayerModel) {
        return FeatureLayer.builder()
                .id(featureLayerModel.getId())
                .fullName(featureLayerModel.getFullName())
                .regionId(featureLayerModel.getRegionId())
                .build();
    }

}
