package com.geo.json.parser.deserializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.geo.json.model.PositionDto;

import java.lang.reflect.Type;

public class PositionDeserializer implements JsonDeserializer<PositionDto> {

	@Override
	public PositionDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		JsonArray asJsonArray = json.getAsJsonArray();
		double[] numbers = new double[asJsonArray.size()];
		for (int i = 0; i < asJsonArray.size(); i++) {
			numbers[i] = asJsonArray.get(i).getAsDouble();
		}

		PositionDto positionDto = new PositionDto();
		positionDto.setNumbers(numbers);
		return positionDto;
	}

}
