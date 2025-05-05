package com.geo.repository;

import com.geo.entity.FeatureLayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureLayerRepository extends JpaRepository<FeatureLayer, Long> {
    @Query("""
        select fl
        from FeatureLayer fl
            left join fl.features f
        where fl.id in (
            select lip.key.layerId
            from LayerInProduct lip
            where lip.product.id = :productId)""")
    List<FeatureLayer> findByProductId(@Param("productId") Long productId);
}
