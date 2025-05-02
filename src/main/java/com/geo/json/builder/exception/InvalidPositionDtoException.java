package com.geo.json.builder.exception;

import com.geo.json.model.PositionDto;

import java.io.Serial;

public class InvalidPositionDtoException extends RuntimeException {
 
	@Serial
	private static final long serialVersionUID = 1L;

	public InvalidPositionDtoException(PositionDto position) {
		super("Invalid position dto: " + position);
	}

}
