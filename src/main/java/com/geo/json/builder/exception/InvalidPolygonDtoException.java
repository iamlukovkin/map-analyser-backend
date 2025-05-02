package com.geo.json.builder.exception;

import lombok.Getter;
import com.geo.json.model.geometry.PolygonDto;

import java.io.Serial;

@Getter
public class InvalidPolygonDtoException extends RuntimeException {
 
	@Serial
	private static final long serialVersionUID = 1L;
	private final PolygonDto polygon;

	public InvalidPolygonDtoException(PolygonDto polygon) {
		super("Invalid polygon dto: " + polygon);
		this.polygon = polygon;
	}

}
