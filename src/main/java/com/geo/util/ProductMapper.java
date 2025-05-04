package com.geo.util;

import com.geo.entity.Product;
import com.geo.model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductMapper implements Function<ProductModel, Product> {
    @Override
    public Product apply(ProductModel productModel) {
        return Product.builder()
                .fullName(productModel.getFullName())
                .price(productModel.getPrice())
                .description(productModel.getDescription())
                .build();
    }
}
