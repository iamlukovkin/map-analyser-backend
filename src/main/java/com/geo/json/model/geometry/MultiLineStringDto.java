package com.geo.json.model.geometry;

import lombok.Getter;
import lombok.Setter;
import com.geo.json.model.GeoJSONObjectTypeEnum;

import java.io.Serial;
import java.util.List;

@Setter
@Getter
public class MultiLineStringDto extends GeometryDto {

	@Serial
	private static final long serialVersionUID = 1L;

	private List<LineStringDto> lines;

    @Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiLineString;
	}
}
