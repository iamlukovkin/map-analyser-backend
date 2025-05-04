package com.geo.service.map.mapper;

import com.geo.entity.FeatureInH3;
import com.geo.model.map.H3AreaModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class H3AreaMapper implements Function<FeatureInH3, H3AreaModel> {
    @Override
    public H3AreaModel apply(FeatureInH3 featureInH3) {
        return H3AreaModel.builder()
                .h3(featureInH3.getH3().getHexIndex())
                .featureId(featureInH3.getFeature().getId())
                .featureMeasurement(featureInH3.getFeatureMeasurement())
                .build();
    }
}
