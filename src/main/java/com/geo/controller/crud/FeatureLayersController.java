package com.geo.controller.crud;


import com.geo.model.FeatureLayerModel;
import com.geo.service.FeatureLayerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/feature_layers")
public class FeatureLayersController {

    private final FeatureLayerService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody FeatureLayerModel model) {
        try {
            service.create(model);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<FeatureLayerModel> read(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok().body(service.read(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<FeatureLayerModel>> readAll() {
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("update")
    public ResponseEntity<String> update(@RequestBody FeatureLayerModel productModel, @RequestParam("id") Long id) {
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
}
