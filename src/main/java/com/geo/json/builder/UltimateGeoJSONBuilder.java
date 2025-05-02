package com.geo.json.builder;

import com.geo.json.builder.common.BuilderConstants;
import com.geo.json.builder.feature.FeatureBuilder;
import com.geo.json.builder.feature.FeatureCollectionBuilder;
import com.geo.json.builder.geometry.*;
import com.geo.json.model.geometry.*;
import com.geo.json.model.GeoJSONObjectDto;
import com.geo.json.model.feature.FeatureCollectionDto;
import com.geo.json.model.feature.FeatureDto;

public class UltimateGeoJSONBuilder {

	private static final UltimateGeoJSONBuilder INSTANCE = new UltimateGeoJSONBuilder();

	public static UltimateGeoJSONBuilder getInstance() {
		return INSTANCE;
	}

	private UltimateGeoJSONBuilder() {
	}

	public String toGeoJSON(GeoJSONObjectDto geoJsonObjectDto) {
        return switch (geoJsonObjectDto) {
            case PointDto pointDto -> PointBuilder.getInstance().toGeoJSON(pointDto);
            case LineStringDto lineStringDto -> LineStringBuilder.getInstance().toGeoJSON(lineStringDto);
            case PolygonDto polygonDto -> PolygonBuilder.getInstance().toGeoJSON(polygonDto);
            case FeatureDto featureDto -> FeatureBuilder.getInstance().toGeoJSON(featureDto);
            case FeatureCollectionDto featureCollectionDto ->
                    FeatureCollectionBuilder.getInstance().toGeoJSON(featureCollectionDto);
            case MultiPointDto multiPointDto -> MultiPointBuilder.getInstance().toGeoJSON(multiPointDto);
            case MultiLineStringDto multiLineStringDto ->
                    MultiLineStringBuilder.getInstance().toGeoJSON(multiLineStringDto);
            case MultiPolygonDto multiPolygonDto -> MultiPolygonBuilder.getInstance().toGeoJSON(multiPolygonDto);
            case GeometryCollectionDto geometryCollectionDto ->
                    GeometryCollectionBuilder.getInstance().toGeoJSON(geometryCollectionDto);
            case null, default -> BuilderConstants.NULL_VALUE;
        };

    }

}
