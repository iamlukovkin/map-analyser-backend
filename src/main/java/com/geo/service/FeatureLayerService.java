package com.geo.service;

import com.geo.mapper.LayerMapModelMapper;
import com.geo.model.LayerMapModel;
import com.geo.repository.FeatureLayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureLayerService {
    private final FeatureLayerRepository featureLayerRepository;

    public List<LayerMapModel> findLayersOfProduct(@RequestParam Long productId) {
        var layers = featureLayerRepository.findByProductId(productId);
        return layers.stream()
                .map(new LayerMapModelMapper())
                .toList();
    }
}
