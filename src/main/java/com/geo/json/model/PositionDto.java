package com.geo.json.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class PositionDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private double[] numbers;

	public PositionDto() {
		this.numbers = new double[2];
	}

	public PositionDto(double longitude, double latitude) {
		this.numbers = new double[] { longitude, latitude };
	}

	public PositionDto(double longitude, double latitude, double elevation) {
		this.numbers = new double[] { longitude, latitude, elevation };
	}

	public PositionDto(PositionDto position) {
		if (position != null) {
			double[] copyNumbers = position.getNumbers();
			this.numbers = new double[copyNumbers.length];
            System.arraycopy(copyNumbers, 0, this.numbers, 0, copyNumbers.length);
		}
	}

	@Override
	public String toString() {
		if (numbers == null) {
			return "null numbers[]";
		}
		StringBuilder value = new StringBuilder("[");
		for (int i = 0; i < numbers.length; i++) {
			value.append(numbers[i]);
			if (i < numbers.length - 1) {
				value.append(", ");
			}
		}
		value.append("]");
		return value.toString();
	}

	public void setLongitude(double longitude) {
		this.numbers[0] = longitude;
	}

	public double getLongitude() {
		return numbers[0];
	}

	public void setLatitude(double latitude) {
		this.numbers[1] = latitude;
	}

	public double getLatitude() {
		return numbers[1];
	}

	public void setElevation(double elevation) {
		this.numbers[2] = elevation;
	}

	public double getElevation() {
		return numbers[2];
	}

}
