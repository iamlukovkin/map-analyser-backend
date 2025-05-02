package com.geo.entity;

import com.geo.entity.keys.LayerInProductKey;
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
@Table(name = "layer_in_product")
public class LayerInProduct {
    @EmbeddedId
    private LayerInProductKey key;

    @JoinColumn(name = "layer_id", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private FeatureLayer featureLayer;

    @JoinColumn(name = "product_id",  updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
