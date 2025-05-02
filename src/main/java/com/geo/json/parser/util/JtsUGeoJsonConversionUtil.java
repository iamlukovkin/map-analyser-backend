package com.geo.json.parser.util;

import com.geo.json.model.geometry.*;
import org.locationtech.jts.geom.*;
import com.geo.json.model.PositionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JtsUGeoJsonConversionUtil {

    private final GeometryFactory geometryFactory;

    public JtsUGeoJsonConversionUtil() {
        this.geometryFactory = new GeometryFactory();
    }

    public JtsUGeoJsonConversionUtil(GeometryFactory geometryFactory) {
        this.geometryFactory = geometryFactory;
    }

    public GeometryDto toGeometryDto(Geometry geometry) {
        if (geometry instanceof Point) {
            return toPointDto((Point) geometry);
        }
        if (geometry instanceof LineString) {
            return toLineStringDto((LineString) geometry);
        }
        if (geometry instanceof Polygon) {
            return toPolygonDto((Polygon) geometry);
        }
        if (geometry instanceof MultiPoint) {
            return toMultiPointDto((MultiPoint) geometry);
        }
        if (geometry instanceof MultiLineString) {
            return toMultiLineStringDto((MultiLineString) geometry);
        }
        if (geometry instanceof MultiPolygon) {
            return toMultiPolygonDto((MultiPolygon) geometry);
        }
        if (geometry instanceof GeometryCollection) {
            return toGeometryCollectionDto((GeometryCollection) geometry);
        }
        return null;
    }

    public Geometry toJtsGeometry(GeometryDto dto) {
        if (dto instanceof PointDto) {
            return toJtsPoint((PointDto) dto);
        }
        if (dto instanceof LineStringDto) {
            return toJtsLineString((LineStringDto) dto);
        }
        if (dto instanceof PolygonDto) {
            return toJtsPolygon((PolygonDto) dto);
        }
        if (dto instanceof MultiPointDto) {
            return toJtsMultiPoint((MultiPointDto) dto);
        }
        if (dto instanceof MultiLineStringDto) {
            return toJtsMultiLineString((MultiLineStringDto) dto);
        }
        if (dto instanceof MultiPolygonDto) {
            return toJTsMultiPolygon((MultiPolygonDto) dto);
        }
        if (dto instanceof GeometryCollectionDto) {
            return toJtsGeometryCollection((GeometryCollectionDto) dto);
        }
        return null;
    }

    public Point toJtsPoint(PointDto pointDto) {
        return geometryFactory.createPoint(toJtsCoordinate(pointDto.getPosition()));
    }

    public LineString toJtsLineString(LineStringDto lineStringDto) {
        List<PositionDto> positions = lineStringDto.getPositions();

        return geometryFactory.createLineString(positions.stream().map(this::toJtsCoordinate).toArray(Coordinate[]::new));
    }

    public LinearRing toJtsLinearRing(LineStringDto lineStringDto) {
        List<PositionDto> positions = lineStringDto.getPositions();

        return geometryFactory.createLinearRing(positions.stream().map(this::toJtsCoordinate).toArray(Coordinate[]::new));
    }

    public Polygon toJtsPolygon(PolygonDto polygonDto) {
        List<LineStringDto> linearRings = polygonDto.getLinearRings();
        if (linearRings.size() == 1) {
            return geometryFactory.createPolygon(toJtsLinearRing(linearRings.getFirst()));
        }

        List<LinearRing> jtsLinearRings = linearRings.stream().map(this::toJtsLinearRing).collect(Collectors.toList());

        LinearRing shell = jtsLinearRings.getFirst();

        jtsLinearRings.removeFirst();

        return geometryFactory.createPolygon(shell, jtsLinearRings.toArray(new LinearRing[0]));
    }

    public MultiPoint toJtsMultiPoint(MultiPointDto multiPointDto) {
        List<PositionDto> positions = multiPointDto.getPositions();

        return geometryFactory.createMultiPoint(positions.stream().map(pos -> geometryFactory.createPoint(toJtsCoordinate(pos))).toArray(Point[]::new));
    }

    public MultiLineString toJtsMultiLineString(MultiLineStringDto multiLineStringDto) {
        List<LineStringDto> lines = multiLineStringDto.getLines();
        return geometryFactory.createMultiLineString(lines.stream().map(this::toJtsLineString).toArray(LineString[]::new));
    }

    public MultiPolygon toJTsMultiPolygon(MultiPolygonDto multiPolygonDto) {
        List<PolygonDto> polygons = multiPolygonDto.getPolygons();
        return geometryFactory.createMultiPolygon(polygons.stream().map(this::toJtsPolygon).toArray(Polygon[]::new));
    }

    public Coordinate toJtsCoordinate(PositionDto positionDto) {
        double[] numbers = positionDto.getNumbers();
        if (numbers.length == 3) {
            return new Coordinate(numbers[0], numbers[1], numbers[2]);
        }

        return new Coordinate(numbers[0], numbers[1]);
    }

    public PointDto toPointDto(Point p) {
        if (Double.isNaN(p.getCoordinate().getZ())) {
            return new PointDto(p.getX(), p.getY());
        } else {
            return new PointDto(p.getX(), p.getY(), p.getCoordinate().getZ());
        }
    }

    public LineStringDto toLineStringDto(LineString lineString) {
        return new LineStringDto(toPositionDtoList(lineString.getCoordinates()));
    }

    public PolygonDto toPolygonDto(Polygon polygon) {
        LineStringDto exteriorRing = toLineStringFromLinearRing(polygon.getExteriorRing());
        int numInteriorRing = polygon.getNumInteriorRing();

        List<LineStringDto> linearRings = new ArrayList<>();
        linearRings.add(exteriorRing);

        for (int i = 0; i < numInteriorRing; i++) {
            LinearRing interiorRingN = polygon.getInteriorRingN(i);
            LineStringDto interiorRing = toLineStringFromLinearRing(interiorRingN);
            linearRings.add(interiorRing);
        }

        PolygonDto polygonDto = new PolygonDto();
        polygonDto.setLinearRings(linearRings);

        return polygonDto;
    }


    public LineStringDto toLineStringFromLinearRing(LinearRing linearRing) {
        return new LineStringDto(toPositionDtoList(linearRing.getCoordinates()));
    }

    public MultiPointDto toMultiPointDto(MultiPoint multiPoint) {
        Coordinate[] coordinates = multiPoint.getCoordinates();
        List<PositionDto> positions = toPositionDtoList(coordinates);
        MultiPointDto dto = new MultiPointDto();
        dto.setPositions(positions);
        return dto;
    }

    public MultiLineStringDto toMultiLineStringDto(MultiLineString multiLineString) {
        List<LineStringDto> lineStrings = new ArrayList<>();
        int numGeometries = multiLineString.getNumGeometries();
        for (int i = 0; i < numGeometries; i++) {
            LineString lineString = (LineString) multiLineString.getGeometryN(i);
            lineStrings.add(toLineStringDto(lineString));
        }
        MultiLineStringDto dto = new MultiLineStringDto();
        dto.setLines(lineStrings);
        return dto;
    }

    public MultiPolygonDto toMultiPolygonDto(MultiPolygon multiPolygon) {
        List<PolygonDto> polygons = new ArrayList<>();
        int numGeometries = multiPolygon.getNumGeometries();
        for (int i = 0; i < numGeometries; i++) {
            Polygon polygon = (Polygon) multiPolygon.getGeometryN(i);
            polygons.add(toPolygonDto(polygon));
        }
        MultiPolygonDto dto = new MultiPolygonDto();
        dto.setPolygons(polygons);
        return dto;
    }

    public GeometryCollectionDto toGeometryCollectionDto(GeometryCollection geometryCollection) {
        int numGeometries = geometryCollection.getNumGeometries();
        List<GeometryDto> geometries = new ArrayList<>();
        for (int i = 0; i < numGeometries; i++) {
            Geometry geometryN = geometryCollection.getGeometryN(i);
            GeometryDto geometryDto = this.toGeometryDto(geometryN);
            geometries.add(geometryDto);
        }

        GeometryCollectionDto dto = new GeometryCollectionDto();
        dto.setGeometries(geometries);
        return dto;
    }

    public GeometryCollection toJtsGeometryCollection(GeometryCollectionDto geometryCollectionDto) {
        List<GeometryDto> geometries = geometryCollectionDto.getGeometries();
        return geometryFactory.createGeometryCollection(geometries.stream().map(this::toJtsGeometry).toArray(Geometry[]::new));
    }

    public PositionDto toPositionDto(Coordinate coordinate) {
        if (Double.isNaN(coordinate.getZ())) {
            return new PositionDto(coordinate.getX(), coordinate.getY());
        } else {
            return new PositionDto(coordinate.getX(), coordinate.getY(), coordinate.getZ());
        }
    }

    private List<PositionDto> toPositionDtoList(Coordinate[] coordinates) {
        List<PositionDto> listDto = new ArrayList<>();

        for (Coordinate c : coordinates) {
            listDto.add(toPositionDto(c));
        }

        return listDto;
    }

}
