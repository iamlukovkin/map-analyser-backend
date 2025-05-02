package com.geo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class H3DataDto {
    private String h3;
    private Double average_measurement;
    private Integer year_of;
}
