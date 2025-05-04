package com.geo.service;

import com.geo.model.ProductModel;
import com.geo.repository.ProductRepository;
import com.geo.util.ProductMapper;
import com.geo.util.ProductModelMapper;
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

    @CacheEvict(cacheNames = "products", allEntries = true)
    public void create(ProductModel model) {
        repository.save(mapper.apply(model));
    }

    @Cacheable(cacheNames = "product", key = "#id", unless = "#result == null")
    public ProductModel read(Long id) throws EntityNotFoundException {
        var entity = repository.findById(id).orElseThrow(() -> entityNotFoundException(id));
        return modelMapper.apply(entity);
    }

    @Cacheable(cacheNames = "products")
    public List<ProductModel> readAll() {
        return repository.findAll()
                .stream()
                .map(modelMapper)
                .collect(Collectors.toList());
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

    @Caching(evict = {@CacheEvict(cacheNames = "product", key = "#id"),
            @CacheEvict(cacheNames = "products", allEntries = true)})
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