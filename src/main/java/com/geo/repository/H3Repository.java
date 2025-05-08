package com.geo.repository;

import com.geo.entity.H3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface H3Repository extends JpaRepository<H3, Long> {

    @Query("""
        select fih.h3
        from FeatureInH3 fih
        where fih.h3.region.id = :regionId
          and fih.feature.id in :features
                  and fih.h3.size = :hexSize
        """)
    List<H3> findH3ByRegionIdAndFeatures(
            @Param("regionId") Long regionId,
            @Param("features") List<Long> features,
            @Param("hexSize") Long hexSize);

    @Query("""
        select fih.h3
        from FeatureInH3 fih
        where fih.h3.region.id in :regions
            and fih.feature.id in :features
            and fih.h3.size = :hexSize
        """)
    List<H3> findH3ByLayerIdsAndHexSize(
            @Param("regions") List<Long> regions,
            @Param("features") List<Long> features,
            @Param("hexSize") Long hexSize);
}
