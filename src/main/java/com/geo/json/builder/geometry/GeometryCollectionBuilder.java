package com.geo.json.builder.geometry;

import com.geo.json.builder.common.BuilderConstants;
import com.geo.json.model.GeoJSONObjectTypeEnum;
import com.geo.json.model.geometry.GeometryCollectionDto;
import com.geo.json.model.geometry.GeometryDto;

import java.util.List;

public class GeometryCollectionBuilder extends GeometryBuilder<GeometryCollectionDto> {

	private static final GeometryCollectionBuilder INSTANCE = new GeometryCollectionBuilder();

	public static GeometryCollectionBuilder getInstance() {
		return INSTANCE;
	}

	private GeometryCollectionBuilder() {
	}

	@Override
	public String toGeoJSON(GeometryCollectionDto geometryCollection) {
		if (geometryCollection == null) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.GeometryCollection);

		builder.append(BuilderConstants.GEOMETRIES_SPACE);
		builder.append(BuilderConstants.OPEN_BRACKET);

		List<GeometryDto> geometries = geometryCollection.getGeometries();
		if (geometries != null) {
			for (int i = 0; i < geometries.size(); i++) {
				String geometryGeoJSON = CommonGeometryBuilder.toGeometryGeoJSON(geometries.get(i));
				builder.append(geometryGeoJSON);
				if (i < geometries.size() - 1) {
					builder.append(BuilderConstants.COMMA);
				}
			}
		}

		builder.append(BuilderConstants.CLOSE_BRACKET);
		
		buildBbox(builder, geometryCollection.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
