package com.geo.repository;

import com.geo.entity.FeatureLayer;
import com.geo.entity.LayerInProduct;
import com.geo.entity.keys.LayerInProductKey;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayerInProductRepository extends JpaRepository<LayerInProduct, LayerInProductKey> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO layer_in_product (product_id, layer_id) VALUES (:productId, :layerId)", nativeQuery = true)
    void addLayerToProduct(@Param("productId") Long productId, @Param("layerId") Long layerId);

    @Query(value = """
            SELECT lip.featureLayer FROM LayerInProduct lip
            WHERE lip.product.id = :productId
            """)
    List<FeatureLayer> getLayerInProduct(@Param("productId") Long productId);
}
