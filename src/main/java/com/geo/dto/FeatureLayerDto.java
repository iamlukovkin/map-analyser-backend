package com.geo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureLayerDto {
    private Long id;
    private Long regionId;
    private String fullName;
    private List<FeatureDto> features;
}