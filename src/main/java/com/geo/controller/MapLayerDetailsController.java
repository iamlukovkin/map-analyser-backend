package com.geo.controller;

import com.geo.model.H3YearlyModel;
import com.geo.model.LayerMapModel;
import com.geo.service.FeatureLayerService;
import com.geo.service.MapDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/map")
public class MapLayerDetailsController {

    private final MapDetailService service;
    private final FeatureLayerService featureLayerService;

    @GetMapping("areas")
    public List<H3YearlyModel> findAreasOfLayer(@RequestParam Long layerId, @RequestParam Long hexSize) {
        return service.getByLayerId(layerId, hexSize);
    }

    @GetMapping("layers")
    public List<LayerMapModel> findLayersOfProduct(@RequestParam Long productId) {
        return featureLayerService.findLayersOfProduct(productId);
    }
}
