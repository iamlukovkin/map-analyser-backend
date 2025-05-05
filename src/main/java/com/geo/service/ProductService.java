package com.geo.service;

import com.geo.mapper.ProductModelMapper;
import com.geo.model.client.ProductModel;
import com.geo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductModelMapper productModelMapper;

    public List<ProductModel> findProductsByWebsiteUser(Long userId) {
        var products = productRepository.findProductsByWebsiteUser(userId);
        return products.stream()
                .map(productModelMapper)
                .collect(Collectors.toList());
    }
}
