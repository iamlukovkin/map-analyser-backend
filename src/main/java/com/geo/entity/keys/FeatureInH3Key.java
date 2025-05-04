package com.geo.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureInH3Key implements Serializable {

    @Column(name = "feature_id")
    private Long featureId;

    @Column(name = "h3_id")
    private Long h3Id;

    @Column(name = "date_of")
    private LocalDateTime dateOf;
}
