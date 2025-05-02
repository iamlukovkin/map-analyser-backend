package com.geo.json.math.draw;

import com.geo.json.model.PositionDto;
import com.geo.json.model.geometry.PointDto;

import java.util.List;

public interface CircularDrawingAlgorithm {

	List<PointDto> getCirclePoints(PointDto center, double radiusInMeters);

	List<PositionDto> getCirclePositions(PositionDto center, double radiusInMeters);

	List<PositionDto> getArcPositions(PositionDto center, double radiusInMeters, double startingAngle,
			double endingAngle);

}
