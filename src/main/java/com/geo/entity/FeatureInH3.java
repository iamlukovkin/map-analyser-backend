package com.geo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.geo.entity.keys.FeatureInH3Key;
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
@Table(name = "feature_in_h3")
public class FeatureInH3 {

    @EmbeddedId
    private FeatureInH3Key featureInH3Key;

    @MapsId("featureId")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_id")
    @JsonBackReference
    private Feature feature;

    @MapsId("h3Id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "h3_id")
    @JsonBackReference
    private H3 h3;

    @Column(name = "feature_measurement")
    private Integer featureMeasurement;

}


