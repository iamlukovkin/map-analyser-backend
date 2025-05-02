package com.geo.json.builder.geometry;

import com.geo.json.builder.common.BuilderConstants;
import com.geo.json.model.geometry.*;

import java.util.HashMap;
import java.util.Map;

public final class CommonGeometryBuilder {

	private static final Map<Class<? extends GeometryDto>, GeometryBuilder<? extends GeometryDto>> builders = new HashMap<>();

	static {
		builders.put(PointDto.class, PointBuilder.getInstance());
		builders.put(LineStringDto.class, LineStringBuilder.getInstance());
		builders.put(PolygonDto.class, PolygonBuilder.getInstance());
		builders.put(MultiPointDto.class, MultiPointBuilder.getInstance());
		builders.put(MultiLineStringDto.class, MultiLineStringBuilder.getInstance());
		builders.put(MultiPolygonDto.class, MultiPolygonBuilder.getInstance());
		builders.put(GeometryCollectionDto.class, GeometryCollectionBuilder.getInstance());
	}

	private CommonGeometryBuilder() {
	}

	public static GeometryBuilder<? extends GeometryDto> getBuilder(GeometryDto geometryDto) {
		if (geometryDto == null) {
			return null;
		}
		return builders.get(geometryDto.getClass());
	}

	public static String toGeometryGeoJSON(GeometryDto geometryDto) {
		GeometryBuilder<? extends GeometryDto> builder = getBuilder(geometryDto);
		if (builder != null) {
			GeometryBuilder<GeometryDto> typedBuilder = (GeometryBuilder<GeometryDto>) builder;
			return typedBuilder.toGeoJSON(geometryDto);
		}
		return BuilderConstants.NULL_VALUE;
	}
}
