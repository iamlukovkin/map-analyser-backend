package com.geo.json.model.feature;

import lombok.Getter;
import lombok.Setter;
import com.geo.json.model.GeoJSONObjectDto;
import com.geo.json.model.GeoJSONObjectTypeEnum;

import java.io.Serial;
import java.util.List;

@Setter
@Getter
public class FeatureCollectionDto extends GeoJSONObjectDto {
 
	@Serial
	private static final long serialVersionUID = 1L;
	
	private List<FeatureDto> features;

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.FeatureCollection;
	}

}
