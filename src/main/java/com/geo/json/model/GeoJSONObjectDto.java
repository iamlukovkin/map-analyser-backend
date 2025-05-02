package com.geo.json.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public abstract class GeoJSONObjectDto implements Serializable {
 
	@Serial
	private static final long serialVersionUID = 1L;
	
	private BoundingBoxDto bbox;

	public abstract GeoJSONObjectTypeEnum getGeoJSONObjectType();

}
