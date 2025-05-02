package com.geo.json.model.geometry;

import lombok.Getter;
import lombok.Setter;
import com.geo.json.model.GeoJSONObjectTypeEnum;

import java.io.Serial;
import java.util.List;

/**
 * @author moksuzer
 *
 */
@Setter
@Getter
public class PolygonDto extends GeometryDto {

	@Serial
	private static final long serialVersionUID = 1L;

	private List<LineStringDto> linearRings;

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.Polygon;
	}

}
