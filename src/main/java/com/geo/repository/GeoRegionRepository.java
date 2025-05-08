package com.geo.repository;

import com.geo.entity.GeoRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoRegionRepository extends JpaRepository<GeoRegion, Long> {
    @Query("""
        select fl.geoRegion
        from FeatureLayer fl
        where fl.id = :layerId
""")
    GeoRegion findGeoRegionByFeatureLayerId(@Param("layerId") Long layerId);

    @Query("""
        select distinct fl.geoRegion.id
        from FeatureLayer fl
        where fl.id in :layerIds
    """)
    List<Long> findRegionsByLayerIds(@Param("layerIds") List<Long> layerIds);
}
