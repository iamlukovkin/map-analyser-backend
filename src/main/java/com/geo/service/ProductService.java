package com.geo.service;

import com.geo.entity.keys.LayerInProductKey;
import com.geo.model.FeatureLayerModel;
import com.geo.model.ProductModel;
import com.geo.repository.LayerInProductRepository;
import com.geo.repository.ProductRepository;
import com.geo.util.FeatureLayerModelMapper;
import com.geo.util.ProductMapper;
import com.geo.util.ProductModelMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "productsCache")
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductModelMapper modelMapper;
    private final LayerInProductRepository layerInProductRepository;

    @CacheEvict(cacheNames = "products", allEntries = true)
    public void create(ProductModel model) {
        repository.save(mapper.apply(model));
    }

    public void addLayer(Long layerId, Long productId) {
        boolean existsById = layerInProductRepository.existsById(new LayerInProductKey(layerId, productId));
        if (existsById) {
            throw new EntityExistsException("Product with id " + productId + " already contains layer with id " + layerId);
        }
        layerInProductRepository.addLayerToProduct(productId, layerId);
    }

    public void deleteLayer(Long layerId, Long productId) {
        boolean exists = layerInProductRepository.existsById(new LayerInProductKey(layerId, productId));
        if (!exists) {
            throw new EntityNotFoundException("Product with id " + productId + " has no layer with id " + layerId);
        }
        layerInProductRepository.deleteById(new LayerInProductKey(layerId, productId));
    }

    public List<FeatureLayerModel> getLayersOfProduct(Long productId) {
        return layerInProductRepository.getLayerInProduct(productId)
                .stream()
                .map(new FeatureLayerModelMapper())
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "product", key = "#id", unless = "#result == null")
    public ProductModel read(Long id) throws EntityNotFoundException {
        var entity = repository.findById(id).orElseThrow(() -> entityNotFoundException(id));
        return modelMapper.apply(entity);
    }

    @Cacheable(cacheNames = "products")
    public List<ProductModel> readAll() {
        return repository.findAll().stream().map(modelMapper).collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "products", allEntries = true)
    public void update(ProductModel model, Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw entityNotFoundException(id);
        }
        var newEntity = mapper.apply(model);
        newEntity.setId(id);
        repository.save(newEntity);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "product", key = "#id"), @CacheEvict(cacheNames = "products", allEntries = true)})
    public void delete(Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw entityNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("Product with id %d not found".formatted(id));
    }
}