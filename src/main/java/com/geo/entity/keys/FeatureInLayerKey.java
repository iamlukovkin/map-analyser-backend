package com.geo.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatureInLayerKey implements Serializable {

    @Column(name = "layer_id")
    private Long layerId;

    @Column(name = "feature_id")
    private Long featureId;

}
