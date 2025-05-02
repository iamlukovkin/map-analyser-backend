package com.geo.controllers;

import com.geo.dto.FlatMapLayerDto;
import com.geo.dto.MapDetailsDto;
import com.geo.repository.LayerInProductRepository;
import com.geo.util.MapDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/map_details")
public class MapDetailsController {

    private final LayerInProductRepository layerInProductRepository;
    private final MapDetailsMapper mapDetailsMapper;

    @GetMapping
    public ResponseEntity<MapDetailsDto> getMapDetails(@RequestParam("product_id") Long productId) {
        try {
            List<FlatMapLayerDto> raw = layerInProductRepository.findMapDataByProductId(productId);
            return ResponseEntity.ok(mapDetailsMapper.toMapDetailsDto(raw));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
