package com.geo.util;

import com.geo.entity.Product;
import com.geo.model.ProductModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductMapperTest {

    private final ProductMapper mapper = new ProductMapper();

    @Test
    void apply_shouldMapProductModelToProductCorrectly() {
        // Given
        ProductModel model = new ProductModel();
        model.setFullName("Sample Product");
        model.setPrice(99.99);
        model.setDescription("A test product");

        // When
        Product result = mapper.apply(model);

        // Then
        assertNotNull(result);
        assertEquals(model.getFullName(), result.getFullName());
        assertEquals(model.getPrice(), result.getPrice());
        assertEquals(model.getDescription(), result.getDescription());
    }

}
