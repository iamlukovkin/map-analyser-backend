package com.geo.json.parser.deserializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.geo.json.model.PositionDto;
import com.geo.json.model.geometry.LineStringDto;
import com.geo.json.model.geometry.PolygonDto;
import com.geo.json.parser.util.BoundingBoxParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PolygonDeserializer implements JsonDeserializer<PolygonDto> {

	@Override
	public PolygonDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		PolygonDto dto = new PolygonDto();
		List<LineStringDto> rings = new ArrayList<>();
		dto.setLinearRings(rings);

		// TODO: remove duplicate
		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		Type positionCollectionType = new TypeToken<List<PositionDto>>() {
		}.getType();
		for (int i = 0; i < jsonArray.size(); i++) {
			List<PositionDto> positions = context.deserialize(jsonArray.get(i), positionCollectionType);
			LineStringDto ring = new LineStringDto();
			ring.setPositions(positions);
			rings.add(ring);
		}
		
		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));
		
		return dto;
	}

}
