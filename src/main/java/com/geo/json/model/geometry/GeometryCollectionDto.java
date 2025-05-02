package com.geo.json.model.geometry;

import lombok.Getter;
import lombok.Setter;
import com.geo.json.model.GeoJSONObjectTypeEnum;

import java.io.Serial;
import java.util.List;

@Setter
@Getter
public class GeometryCollectionDto extends GeometryDto {

	@Serial
	private static final long serialVersionUID = 1L;

	private List<GeometryDto> geometries;

    @Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.GeometryCollection;
	}
}
