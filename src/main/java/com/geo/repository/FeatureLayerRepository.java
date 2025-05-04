package com.geo.repository;

import com.geo.entity.FeatureLayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureLayerRepository extends JpaRepository<FeatureLayer, Long> {
}
