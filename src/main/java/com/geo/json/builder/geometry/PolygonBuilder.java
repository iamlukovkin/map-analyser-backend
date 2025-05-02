package com.geo.json.builder.geometry;

import com.geo.json.builder.common.BuilderConstants;
import com.geo.json.model.GeoJSONObjectTypeEnum;
import com.geo.json.model.geometry.LineStringDto;
import com.geo.json.model.geometry.PolygonDto;

import java.util.List;

public class PolygonBuilder extends GeometryBuilder<PolygonDto> {

	private static final PolygonBuilder INSTANCE = new PolygonBuilder();

	public static PolygonBuilder getInstance() {
		return INSTANCE;
	}

	private PolygonBuilder() {
	}

	@Override
	public String toGeoJSON(PolygonDto polygon) {
		if (polygon == null || polygon.getLinearRings() == null || polygon.getLinearRings().isEmpty()) {
			return BuilderConstants.NULL_VALUE;
		}

		checkAndCorrectLinearRing(polygon);

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.Polygon);

		builder.append(BuilderConstants.COORDINATES_SPACE);
		builder.append(BuilderConstants.OPEN_BRACKET);
		builder.append(BuilderConstants.NEWLINE);

		List<LineStringDto> linearRings = polygon.getLinearRings();

		for (int i = 0; i < linearRings.size(); i++) {
			builder.append(BuilderConstants.OPEN_BRACKET);
			builder.append(BuilderConstants.NEWLINE);

			LineStringDto lineStringDto = linearRings.get(i);

			buildLineStringPositions(builder, lineStringDto);

			builder.append(BuilderConstants.CLOSE_BRACKET);
			if (i < linearRings.size() - 1) {
				builder.append(BuilderConstants.COMMA_NEWLINE);
			} else {
				builder.append(BuilderConstants.NEWLINE);
			}

		}

		builder.append(BuilderConstants.CLOSE_BRACKET);

		buildBbox(builder, polygon.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
