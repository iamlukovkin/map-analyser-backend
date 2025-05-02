package com.geo.json.model;

import com.geo.json.model.geometry.*;
import lombok.Getter;
import com.geo.json.model.feature.FeatureCollectionDto;
import com.geo.json.model.feature.FeatureDto;

@Getter
public enum GeoJSONObjectTypeEnum {

	Point(PointDto.class), MultiPoint(MultiPointDto.class), LineString(LineStringDto.class), MultiLineString(
			MultiLineStringDto.class), Polygon(PolygonDto.class), MultiPolygon(
					MultiPolygonDto.class), GeometryCollection(GeometryCollectionDto.class), Feature(
							FeatureDto.class), FeatureCollection(FeatureCollectionDto.class);

	private final Class<? extends GeoJSONObjectDto> dtoClass;

	GeoJSONObjectTypeEnum(Class<? extends GeoJSONObjectDto> dtoClass) {
		this.dtoClass = dtoClass;
	}

	@Override
	public String toString() {
		return this.name();
	}


}
