package com.geo.service;

import com.geo.model.FeatureModel;
import com.geo.repository.FeatureRepository;
import com.geo.util.FeatureMapper;
import com.geo.util.FeatureModelMapper;
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
@CacheConfig(cacheNames = "featuresCache")
public class FeatureService {
    private final FeatureRepository repository;
    private final FeatureMapper mapper;
    private final FeatureModelMapper modelMapper;

    @CacheEvict(cacheNames = "features", allEntries = true)
    public void create(FeatureModel model) {
        repository.save(mapper.apply(model));
    }

    @Cacheable(cacheNames = "feature", key = "#id", unless = "#result == null")
    public FeatureModel read(Long id) throws EntityNotFoundException {
        var entity = repository.findById(id).orElseThrow(() -> entityNotFoundException(id));
        return modelMapper.apply(entity);
    }

    @Cacheable(cacheNames = "features")
    public List<FeatureModel> readAll() {
        return repository.findAll()
                .stream()
                .map(modelMapper)
                .collect(Collectors.toList());
    }

    @CacheEvict(cacheNames = "features", allEntries = true)
    public void update(FeatureModel model, Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw entityNotFoundException(id);
        }
        var newEntity = mapper.apply(model);
        newEntity.setId(id);
        repository.save(newEntity);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "feature", key = "#id"), @CacheEvict(cacheNames = "features", allEntries = true)})
    public void delete(Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw entityNotFoundException(id);
        }
        repository.deleteById(id);
    }

    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("Feature with id %d not found".formatted(id));
    }
}
