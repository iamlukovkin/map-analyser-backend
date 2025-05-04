package com.geo.repository;

import com.geo.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

    @Query("""
            select source
            from Feature source
            where source.id in (
                select fil.key.featureId
                from FeatureInLayer fil
                where fil.key.layerId = :layerId
            )
            """)
    List<Feature> findFeaturesByLayerId(@Param("layerId") Long layerId);
}
