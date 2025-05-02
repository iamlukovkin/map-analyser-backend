package com.geo.json.model.geometry;

import lombok.Getter;
import lombok.Setter;
import com.geo.json.model.GeoJSONObjectTypeEnum;
import com.geo.json.model.PositionDto;

import java.io.Serial;

@Setter
@Getter
public class PointDto extends GeometryDto {

	@Serial
	private static final long serialVersionUID = 1L;

	private PositionDto position;

	public PointDto() {
		this.position = new PositionDto();
	}

	public PointDto(double longitude, double latitude) {
		this.position = new PositionDto(longitude, latitude);
	}

	public PointDto(double longitude, double latitude, double elevation) {
		this.position = new PositionDto(longitude, latitude, elevation);
	}

	public PointDto(PointDto point) {
		this.position = new PositionDto(point.getPosition());
	}

	public PointDto(PositionDto position) {
		 this.position = new PositionDto(position);
	}

	public double getLongitude() {
		return this.position.getLongitude();
	}

	public void setLongitude(double longitude) {
		this.position.setLongitude(longitude);
	}

	public double getLatitude() {
		return this.position.getLatitude();
	}

	public void setLatitude(double latitude) {
		this.position.setLatitude(latitude);
	}

	public void setElevation(double elevation) {
		this.position.setElevation(elevation);
	}

	public double getElevation() {
		return this.position.getElevation();
	}

    @Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.Point;
	}

}
