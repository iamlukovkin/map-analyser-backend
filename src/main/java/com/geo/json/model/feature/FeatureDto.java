package com.geo.json.model.feature;

import lombok.Getter;
import lombok.Setter;
import com.geo.json.model.GeoJSONObjectDto;
import com.geo.json.model.GeoJSONObjectTypeEnum;
import com.geo.json.model.geometry.GeometryDto;

import java.io.Serial;

@Setter
@Getter
public class FeatureDto extends GeoJSONObjectDto {

	@Serial
	private static final long serialVersionUID = 1L;

	private GeometryDto geometry;
	private String properties;
	private String id;

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.Feature;
	}

}
