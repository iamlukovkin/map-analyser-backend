package com.geo.controller.crud;

import com.geo.model.FeatureLayerModel;
import com.geo.model.ProductModel;
import com.geo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/products")
public class ProductsController {
    private final ProductService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ProductModel productModel) {
        try {
            service.create(productModel);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ProductModel> read(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok().body(service.read(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("update")
    public ResponseEntity<String> update(@RequestBody ProductModel productModel, @RequestParam("id") Long id) {
        try {
            service.update(productModel, id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductModel>> readAll() {
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/add_layer")
    public ResponseEntity<String> addLayerToProduct(@RequestParam Long productId, @RequestParam Long layerId) {
        try {
            service.addLayer(layerId, productId);
            return ResponseEntity.ok("Layer added to product successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/delete-layer")
    public ResponseEntity<String> deleteLayerFromProduct(@RequestParam Long productId, @RequestParam Long layerId) {
        try {
            service.deleteLayer(layerId, productId);
            return ResponseEntity.ok("Layer deleted from product successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/layers")
    public ResponseEntity<List<FeatureLayerModel>> readAllLayers(@RequestParam Long productId) {
        try {
            return ResponseEntity.ok(service.getLayersOfProduct(productId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
