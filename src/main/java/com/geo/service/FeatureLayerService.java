package com.geo.service;

import com.geo.model.FeatureLayerModel;
import com.geo.repository.FeatureLayerRepository;
import com.geo.util.FeatureLayerMapper;
import com.geo.util.FeatureLayerModelMapper;
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
@CacheConfig(cacheNames = "featureLayersCache")
public class FeatureLayerService {

    private final FeatureLayerRepository repository;
    private final FeatureLayerMapper mapper;
    private final FeatureLayerModelMapper modelMapper;

    @CacheEvict(cacheNames = "featureLayers", allEntries = true)
    public void create(FeatureLayerModel model) {
        repository.save(mapper.apply(model));
    }

    @Cacheable(cacheNames = "featureLayer", key = "#id", unless = "#result == null")
    public FeatureLayerModel read(Long id) throws EntityNotFoundException {
        var entity = repository.findById(id).orElseThrow(() -> entityNotFoundException(id));
        return modelMapper.apply(entity);
    }

    @Cacheable(cacheNames = "featureLayers")
    public List<FeatureLayerModel> readAll() {
        return repository.findAll()
                .stream()
                .map(modelMapper)
                .collect(Collectors.toList());
    }

    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("Layer with id %d not found".formatted(id));
    }

    @CacheEvict(cacheNames = "featureLayers", allEntries = true)
    public void update(FeatureLayerModel model, Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw entityNotFoundException(id);
        }
        var newEntity = mapper.apply(model);
        newEntity.setId(id);
        repository.save(newEntity);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "featureLayer", key = "#id"),
            @CacheEvict(cacheNames = "featureLayers", allEntries = true)})
    public void delete(Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw entityNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
