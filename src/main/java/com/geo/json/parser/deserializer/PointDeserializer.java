package com.geo.json.parser.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.geo.json.model.PositionDto;
import com.geo.json.model.geometry.PointDto;
import com.geo.json.parser.util.BoundingBoxParser;

import java.lang.reflect.Type;

public class PointDeserializer implements JsonDeserializer<PointDto> {

	@Override
	public PointDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		PointDto point = new PointDto();

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonElement jsonElement = asJsonObject.get("coordinates");
		PositionDto positionDto = context.deserialize(jsonElement, PositionDto.class);
		point.setPosition(positionDto);

		point.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return point;
	}

}
