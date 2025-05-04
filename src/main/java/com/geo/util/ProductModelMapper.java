package com.geo.util;

import com.geo.entity.Product;
import com.geo.model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductModelMapper implements Function<Product, ProductModel> {
    @Override
    public ProductModel apply(Product product) {
        return ProductModel.builder()
                .fullName(product.getFullName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
