package com.geo.model.client;

import com.geo.model.LayerMapModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
    private Long id;
    private String fullName;
    private String description;
    private Double price;
    private List<LayerMapModel> layers;
}
