package com.geo.controller;

import com.geo.model.client.ProductModel;
import com.geo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/products")
public class ProductsController {

    private final ProductService service;

    @GetMapping("all")
    public List<ProductModel> findAllProductsByUserId(@RequestParam Long userId) {
        return service.findProductsByWebsiteUser(userId);
    }
}
