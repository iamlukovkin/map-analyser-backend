package com.geo.controller.crud;

import com.geo.model.FeatureModel;
import com.geo.service.FeatureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/features")
public class FeaturesController {
    private final FeatureService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody FeatureModel productModel) {
        try {
            service.create(productModel);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<FeatureModel> read(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.ok().body(service.read(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("update")
    public ResponseEntity<String> update(@RequestBody FeatureModel productModel, @RequestParam("id") Long id) {
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
    public ResponseEntity<List<FeatureModel>> readAll() {
        try {
            return ResponseEntity.ok(service.readAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
