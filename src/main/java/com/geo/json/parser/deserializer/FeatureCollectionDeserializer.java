package com.geo.json.parser.deserializer;

import com.google.gson.*;
import com.geo.json.model.feature.FeatureCollectionDto;
import com.geo.json.model.feature.FeatureDto;
import com.geo.json.parser.util.BoundingBoxParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FeatureCollectionDeserializer implements JsonDeserializer<FeatureCollectionDto> {

	@Override
	public FeatureCollectionDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		FeatureCollectionDto dto = new FeatureCollectionDto();
		List<FeatureDto> features = new ArrayList<>();
		dto.setFeatures(features);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("features").getAsJsonArray();
		if (jsonArray == null) {
			return dto;
		}

		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject featureElement = jsonArray.get(i).getAsJsonObject();
			FeatureDto geometryDto = context.deserialize(featureElement, FeatureDto.class);
			features.add(geometryDto);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return dto;
	}

}
