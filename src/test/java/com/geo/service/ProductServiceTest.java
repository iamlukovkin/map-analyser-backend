package com.geo.service;

import com.geo.model.ProductModel;
import com.geo.entity.Product; // Make sure this matches your actual entity
import com.geo.repository.ProductRepository;
import com.geo.util.ProductMapper;
import com.geo.util.ProductModelMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @Mock
    private ProductModelMapper modelMapper;

    @InjectMocks
    private ProductService service;

    private Product product;
    private ProductModel productModel;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        productModel = new ProductModel();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void create_shouldSaveEntity() {
        when(mapper.apply(productModel)).thenReturn(product);

        service.create(productModel);

        verify(repository).save(product);
    }

    @Test
    void read_shouldReturnModel_whenFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(modelMapper.apply(product)).thenReturn(productModel);
        var result = service.read(1L);
        assertEquals(productModel, result);
    }

    @Test
    void read_shouldThrowException_whenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.read(1L));
    }

    @Test
    void readAll_shouldReturnMappedList() {
        when(repository.findAll()).thenReturn(List.of(product));
        when(modelMapper.apply(product)).thenReturn(productModel);

        var result = service.readAll();

        assertEquals(1, result.size());
        assertEquals(productModel, result.getFirst());
    }

    @Test
    void update_shouldSaveEntity_whenExists() {
        when(repository.existsById(1L)).thenReturn(true);
        when(mapper.apply(productModel)).thenReturn(product);

        service.update(productModel, 1L);

        verify(repository).save(product);
    }

    @Test
    void update_shouldThrowException_whenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.update(productModel, 1L));
    }

    @Test
    void delete_shouldRemoveEntity_whenExists() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void delete_shouldThrowException_whenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> service.delete(1L));
    }
}
