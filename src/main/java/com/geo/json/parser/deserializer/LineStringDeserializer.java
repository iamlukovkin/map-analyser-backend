package com.geo.json.parser.deserializer;

import com.google.gson.*;
import com.geo.json.model.PositionDto;
import com.geo.json.model.geometry.LineStringDto;
import com.geo.json.parser.util.BoundingBoxParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LineStringDeserializer implements JsonDeserializer<LineStringDto> {

	@Override
	public LineStringDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		LineStringDto dto = new LineStringDto();
		List<PositionDto> positions = new ArrayList<>();

		// TODO: remove duplicate
		dto.setPositions(positions);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			PositionDto position = context.deserialize(jsonArray.get(i), PositionDto.class);
			positions.add(position);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return dto;
	}

}
