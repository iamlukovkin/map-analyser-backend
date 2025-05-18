package com.geo.controller;

import com.geo.dto.FeatureDto;
import com.geo.dto.FeatureLayerDto;
import com.geo.dto.ProductDto;
import com.geo.entity.Product;
import com.geo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/products")
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .fullName(product.getFullName())
                .description(product.getDescription())
                .price(product.getPrice())
                .featureLayers(product.getFeatureLayers().stream()
                        .map(layer -> FeatureLayerDto.builder()
                                .id(layer.getId())
                                .regionId(layer.getRegionId())
                                .fullName(layer.getFullName())
                                .features(layer.getFeatures().stream()
                                        .map(feature -> FeatureDto.builder()
                                                .id(feature.getId())
                                                .fullName(feature.getFullName())
                                                .locatedName(feature.getLocatedName())
                                                .build())
                                        .toList())
                                .build())
                        .toList())
                .build();
    }


}
