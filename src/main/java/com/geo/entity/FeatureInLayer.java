package com.geo.entity;

import com.geo.entity.keys.FeatureInLayerKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}
