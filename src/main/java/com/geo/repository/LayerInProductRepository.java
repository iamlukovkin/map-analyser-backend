package com.geo.repository;

import com.geo.dto.FlatMapLayerDto;
import com.geo.entity.LayerInProduct;
import com.geo.entity.keys.LayerInProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LayerInProductRepository extends JpaRepository<LayerInProduct, LayerInProductKey> {
    @Query("""
    SELECT new com.geo.dto.FlatMapLayerDto(
         fl.id,
         fl.fullName,
         f.id,
         f.fullName,
         f.locatedName,
         fih.featureInH3Key.h3HexIndex,
         extract(year from fih.featureInH3Key.dateOf) ,
         AVG(fih.featureMeasurement)
     )
    FROM LayerInProduct lip
    JOIN FeatureInLayer fil ON fil.key.layerId = lip.key.layerId
    JOIN Feature f ON fil.key.featureId = f.id
    JOIN FeatureInH3 fih ON fih.featureInH3Key.featureId = f.id
    JOIN FeatureLayer fl ON fil.key.layerId = fl.id
    WHERE lip.product.id = :productId
    GROUP BY fl.id, fl.fullName, f.id, f.fullName, f.locatedName, fih.featureInH3Key.h3HexIndex, fih.featureInH3Key.dateOf
""")
    List<FlatMapLayerDto> findMapDataByProductId(@Param("productId") Long productId);


}
