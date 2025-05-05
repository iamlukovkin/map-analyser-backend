package com.geo.mapper;

import com.geo.entity.Product;
import com.geo.model.client.ProductModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductModelMapper implements Function<Product, ProductModel> {

    private final LayerMapModelMapper layerMapModelMapper;

    @Override
    public ProductModel apply(Product product) {
        return ProductModel.builder()
                .id(product.getId())
                .price(product.getPrice())
                .fullName(product.getFullName())
                .description(product.getDescription())
                .layers(product.getFeatureLayers().stream().map(layerMapModelMapper).collect(Collectors.toList()))
                .build();
    }
}
