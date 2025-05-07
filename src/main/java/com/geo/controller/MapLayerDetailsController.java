package com.geo.controller;

import com.geo.mapper.H3YearlyModelMapper;
import com.geo.model.LayerMapModel;
import com.geo.service.FeatureLayerService;
import com.geo.service.MapDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/map")
public class MapLayerDetailsController {

    private final MapDetailService service;
    private final FeatureLayerService featureLayerService;
    private final H3YearlyModelMapper mapper;

    @GetMapping("areas")
    public HashMap<String, HashMap<Long, HashMap<Integer, Double>>> findAreasOfLayer(@RequestParam Long layerId, @RequestParam Long hexSize) {
        var relations = service.getByLayerId(layerId, hexSize);
        HashMap<String, HashMap<Long, HashMap<Integer, Double>>> map = new HashMap<>();
        mapper.setSourceMap(map);
        relations.forEach(relation -> map.put(relation.getH3(), mapper.apply(relation)));
        return map;
    }

    @GetMapping("layers")
    public List<LayerMapModel> findLayersOfProduct(@RequestParam Long productId) {
        return featureLayerService.findLayersOfProduct(productId);
    }
}
