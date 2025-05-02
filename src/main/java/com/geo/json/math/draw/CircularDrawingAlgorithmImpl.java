package com.geo.json.math.draw;

import com.geo.json.math.util.GeoCalculator;
import com.geo.json.model.PositionDto;
import com.geo.json.model.geometry.PointDto;

import java.util.ArrayList;
import java.util.List;

public class CircularDrawingAlgorithmImpl implements CircularDrawingAlgorithm {

	private static final double FULL_CIRCLE_ANGLE = 360;
	private static final double INITIAL_ANGLE = 0;

	@Override
	public List<PointDto> getCirclePoints(PointDto center, double radiusInMeters) {
		List<PointDto> circlePoints = new ArrayList<>();

		PointDto firstPoint = GeoCalculator.getDestinationPoint(center, radiusInMeters, INITIAL_ANGLE);
		circlePoints.add(firstPoint);
		for (double i = 1; i < FULL_CIRCLE_ANGLE; i++) {
			PointDto destination = GeoCalculator.getDestinationPoint(center, radiusInMeters, i);
			circlePoints.add(destination);
		}
		circlePoints.add(firstPoint);

		return circlePoints;
	}

	@Override
	public List<PositionDto> getArcPositions(PositionDto center, double radiusInMeters, double startingAngle,
			double endingAngle) {
		List<PositionDto> arcPositions = new ArrayList<>();
		arcPositions.add(center);
		for (double i = startingAngle; i <= endingAngle; i++) {
			PositionDto destination = GeoCalculator.getDestinationPosition(center, radiusInMeters, i);
			arcPositions.add(destination);
		}
		arcPositions.add(center);
		return arcPositions;
	}

	@Override
	public List<PositionDto> getCirclePositions(PositionDto center, double radiusInMeters) {
		List<PositionDto> circlePoints = new ArrayList<>();
		PositionDto firstPoint = GeoCalculator.getDestinationPosition(center, radiusInMeters, INITIAL_ANGLE);
		circlePoints.add(firstPoint);

		for (double i = 1; i < FULL_CIRCLE_ANGLE; i++) {
			PositionDto destination = GeoCalculator.getDestinationPosition(center, radiusInMeters, i);
			circlePoints.add(destination);
		}

		circlePoints.add(firstPoint);

		return circlePoints;
	}

}
