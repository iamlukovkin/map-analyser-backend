package com.geo.mapper;

import com.geo.entity.FeatureLayer;
import com.geo.model.LayerMapModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LayerMapModelMapper implements Function<FeatureLayer, LayerMapModel> {
    @Override
    public LayerMapModel apply(FeatureLayer featureLayer) {
        var mapper = new FeatureMapModelMapper();
        return LayerMapModel.builder()
                .id(featureLayer.getId())
                .fullName(featureLayer.getFullName())
                .features(featureLayer.getFeatureInLayers()
                        .stream()
                        .map(relation -> mapper.apply(relation.getFeature()))
                        .collect(Collectors.toList()))
                .build();
    }
}
