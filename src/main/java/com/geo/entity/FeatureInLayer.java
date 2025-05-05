package com.geo.entity;

import com.geo.entity.keys.FeatureInLayerKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feature_in_layer")
public class FeatureInLayer {

    @EmbeddedId
    private FeatureInLayerKey key;

    @MapsId("featureId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Feature feature;

}
