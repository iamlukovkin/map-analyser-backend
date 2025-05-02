package com.geo.json.model.geometry;

import lombok.Getter;
import lombok.Setter;
import com.geo.json.model.GeoJSONObjectTypeEnum;
import com.geo.json.model.PositionDto;

import java.io.Serial;
import java.util.List;

@Setter
@Getter
public class MultiPointDto extends GeometryDto {

	@Serial
	private static final long serialVersionUID = 1L;

	private List<PositionDto> positions;

    @Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiPoint;
	}
}
