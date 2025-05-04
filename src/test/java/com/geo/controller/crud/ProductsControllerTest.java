package com.geo.controller.crud;

import com.geo.model.ProductModel;
import com.geo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;

@WebMvcTest(ProductsController.class)
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService service;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private ProductModel productModel;

    @BeforeEach
    void setUp() {
        productModel = new ProductModel();
        productModel.setFullName("Test Product");
        productModel.setDescription("Sample description");
        productModel.setPrice(10.0);
    }

    @Test
    void createProduct_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productModel)))
                .andExpect(status().isOk());
    }

    @Test
    void readProduct_shouldReturnProduct() throws Exception {
        Mockito.when(service.read(1L)).thenReturn(productModel);

        mockMvc.perform(get("/api/products")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName").value("Test Product"));
    }

    @Test
    void readProduct_notFound() throws Exception {
        Mockito.when(service.read(1L)).thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/api/products")
                        .param("id", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateProduct_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/products/update")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productModel)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProduct_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/products/delete")
                        .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void readAll_shouldReturnList() throws Exception {
        Mockito.when(service.readAll()).thenReturn(List.of(productModel));

        mockMvc.perform(get("/api/products/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName").value("Test Product"));
    }
}
