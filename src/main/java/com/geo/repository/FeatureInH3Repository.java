package com.geo.repository;

import com.geo.entity.FeatureInH3;
import com.geo.entity.H3;
import com.geo.entity.keys.FeatureInH3Key;
import com.geo.model.H3YearlyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureInH3Repository extends JpaRepository<FeatureInH3, FeatureInH3Key> {

    @Query("""
            select new com.geo.model.H3YearlyModel(
                year(fih.featureInH3Key.dateOf),
                avg(fih.featureMeasurement),
                fih.feature.id,
                fih.h3.hexIndex)
            from FeatureInH3 fih
            where fih.h3 in :hexes
                and fih.feature.id in :features
            group by year(fih.featureInH3Key.dateOf),
            fih.feature.id,
            fih.h3.hexIndex
            """)
    List<H3YearlyModel> findMatchedRelationsOf(@Param("hexes") List<H3> hexes, @Param("features") List<Long> features);
}
