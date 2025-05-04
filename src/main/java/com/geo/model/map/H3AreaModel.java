package com.geo.model.map;

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
public class H3AreaModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String h3;
    private Long featureId;
    private Integer featureMeasurement;
}
