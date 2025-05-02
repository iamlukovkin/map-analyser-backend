package com.geo.json.math.util;

import com.geo.json.model.PositionDto;
import com.geo.json.model.geometry.PointDto;

public final class GeoCalculator {

	private static final Double EARTH_RADIUS = 6371000.0;

	private GeoCalculator() {
	}

	public static PointDto getDestinationPoint(PointDto startingPoint, double distanceInMeters,
			double bearingInDegrees) {
		PositionDto destinationPosition = getDestinationPosition(startingPoint.getPosition(), distanceInMeters,
				bearingInDegrees);
		PointDto destination = new PointDto();
		destination.setPosition(destinationPosition);
		return destination;
	}

	public static PositionDto getDestinationPosition(PositionDto startingPoint, double distanceInMeters,
			double bearingInDegrees) {
		PositionDto destination = new PositionDto(startingPoint);
		double bearing = Math.toRadians(bearingInDegrees);
		double latInRadians = Math.toRadians(startingPoint.getLatitude());
		double lonInRadians = Math.toRadians(startingPoint.getLongitude());

		double angularRadius = distanceInMeters / EARTH_RADIUS;

		double lat2Radian = Math.asin(Math.sin(latInRadians) * Math.cos(angularRadius)
				+ Math.cos(latInRadians) * Math.sin(angularRadius) * Math.cos(bearing));

		double longitude = Math.atan2(Math.sin(bearing) * Math.sin(angularRadius) * Math.cos(latInRadians),
				Math.cos(angularRadius) - Math.sin(latInRadians) * Math.sin(lat2Radian));

		double lon2Radian = (lonInRadians + longitude + Math.PI) % (2 * Math.PI) - Math.PI;

		destination.setLatitude(Math.toDegrees(lat2Radian));
		destination.setLongitude(Math.toDegrees(lon2Radian));

		return destination;
	}

	public static double distance(PositionDto positionFrom, PositionDto positionTo) {
		double latRadianFrom = Math.toRadians(positionFrom.getLatitude());
		double lonRadianFrom = Math.toRadians(positionFrom.getLongitude());

		double latitudeToRadian = Math.toRadians(positionTo.getLatitude());
		double longitudeToRadian = Math.toRadians(positionTo.getLongitude());

		return Math
				.acos(Math.sin(latRadianFrom) * Math.sin(latitudeToRadian)
						+ Math.cos(latRadianFrom) * Math.cos(latitudeToRadian) * Math.cos(lonRadianFrom - longitudeToRadian))
				* EARTH_RADIUS;
	}

	public static double distance(PointDto pointFrom, PointDto pointTo) {
		return distance(pointFrom.getPosition(), pointTo.getPosition());
	}

}
