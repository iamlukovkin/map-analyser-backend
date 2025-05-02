package com.geo.json.parser.deserializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.geo.json.model.PositionDto;
import com.geo.json.model.geometry.LineStringDto;
import com.geo.json.model.geometry.MultiPolygonDto;
import com.geo.json.model.geometry.PolygonDto;
import com.geo.json.parser.util.BoundingBoxParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MultiPolygonDeserializer implements JsonDeserializer<MultiPolygonDto> {

	@Override
	public MultiPolygonDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		MultiPolygonDto dto = new MultiPolygonDto();
		List<PolygonDto> polygons = new ArrayList<>();
		dto.setPolygons(polygons);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		Type positionCollectionType = new TypeToken<List<PositionDto>>() {
		}.getType();
		for (int i = 0; i < jsonArray.size(); i++) {
			PolygonDto polygonDto = new PolygonDto();
			List<LineStringDto> rings = new ArrayList<>();
			polygonDto.setLinearRings(rings);
			JsonArray jsonPolygonElement = jsonArray.get(i).getAsJsonArray();

			// TODO: remove duplicate
			for (int j = 0; j < jsonPolygonElement.size(); j++) {
				List<PositionDto> positions = context.deserialize(jsonPolygonElement.get(j), positionCollectionType);
				LineStringDto ring = new LineStringDto();
				ring.setPositions(positions);
				rings.add(ring);
			}

			polygons.add(polygonDto);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));
		
		return dto;
	}

}
