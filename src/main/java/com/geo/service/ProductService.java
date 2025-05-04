package com.geo.service;

import com.geo.model.ProductModel;
import com.geo.repository.ProductRepository;
import com.geo.util.ProductMapper;
import com.geo.util.ProductModelMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductModelMapper modelMapper;

    public void create(ProductModel model) {
        var entity = mapper.apply(model);
        repository.save(entity);
    }

    public ProductModel read(Long id) throws EntityNotFoundException {
        var entity = repository.findById(id)
                .orElseThrow(() -> entityNotFoundException(id));
        return modelMapper.apply(entity);
    }

    public List<ProductModel> readAll() {
        return repository.findAll()
                .stream()
                .map(modelMapper)
                .toList();
    }

    public void update(ProductModel model, Long id) throws EntityNotFoundException {
        if (!repository.existsById(id)) {
            throw entityNotFoundException(id);
        }
        var newEntity = mapper.apply(model);
        newEntity.setId(id);
        repository.save(newEntity);
    }

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
