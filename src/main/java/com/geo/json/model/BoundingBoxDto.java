package com.geo.json.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class BoundingBoxDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private PositionDto southWestCorner;
	private PositionDto northEastCorner;

}
