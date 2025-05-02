package com.geo.json.parser.deserializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.geo.json.model.PositionDto;
import com.geo.json.model.geometry.LineStringDto;
import com.geo.json.model.geometry.MultiLineStringDto;
import com.geo.json.parser.util.BoundingBoxParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MultiLineStringDeserializer implements JsonDeserializer<MultiLineStringDto> {

	@Override
	public MultiLineStringDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		MultiLineStringDto dto = new MultiLineStringDto();
		List<LineStringDto> lines = new ArrayList<>();
		dto.setLines(lines);

		// TODO: remove duplicate
		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		Type positionCollectionType = new TypeToken<List<PositionDto>>() {
		}.getType();
		for (int i = 0; i < jsonArray.size(); i++) {
			List<PositionDto> positions = context.deserialize(jsonArray.get(i), positionCollectionType);
			LineStringDto line = new LineStringDto();
			line.setPositions(positions);
			lines.add(line);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));
		
		return dto;
	}

}
