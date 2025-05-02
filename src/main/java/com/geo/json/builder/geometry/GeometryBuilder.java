package com.geo.json.builder.geometry;

import com.geo.json.builder.common.BuilderConstants;
import com.geo.json.builder.common.GeoJSONBuilder;
import com.geo.json.builder.exception.InvalidPolygonDtoException;
import com.geo.json.model.PositionDto;
import com.geo.json.model.geometry.GeometryDto;
import com.geo.json.model.geometry.LineStringDto;
import com.geo.json.model.geometry.PolygonDto;

import java.util.List;

public abstract class GeometryBuilder<T extends GeometryDto> extends GeoJSONBuilder<T> {

	private static final int CORRECTABLE_LINEAR_RING_SIZE = 3;

	@Override
	public abstract String toGeoJSON(T geometry);

	protected void buildLineStringPositions(StringBuilder builder, LineStringDto lineStringDto) {
		List<PositionDto> positions = lineStringDto.getPositions();
		for (int j = 0; j < positions.size(); j++) {
			PositionDto position = positions.get(j);
			builder.append(BuilderConstants.SPACE);
			builder.append(PositionBuilder.getInstance().position(position));
			if (j < positions.size() - 1) {
				builder.append(BuilderConstants.COMMA_NEWLINE);
			} else {
				builder.append(BuilderConstants.NEWLINE);
			}
		}
	}

	protected void checkAndCorrectLinearRing(PolygonDto polygon) {
		List<LineStringDto> linearRings = polygon.getLinearRings();
		for (LineStringDto lineStringDto : linearRings) {
			List<PositionDto> positions = lineStringDto.getPositions();
			if (positions.size() < CORRECTABLE_LINEAR_RING_SIZE) {
				throw new InvalidPolygonDtoException(polygon);
			} else if (positions.size() == CORRECTABLE_LINEAR_RING_SIZE) {
				PositionDto firstPosition = positions.getFirst();
				positions.add(new PositionDto(firstPosition));
			}
		}
	}

}
