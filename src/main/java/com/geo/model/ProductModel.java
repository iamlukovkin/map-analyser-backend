package com.geo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String fullName;
    private String description;
    private Double price;
}
