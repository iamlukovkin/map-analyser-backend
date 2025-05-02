package com.geo.json.parser.deserializer;

import com.google.gson.*;
import com.geo.json.model.GeoJSONObjectTypeEnum;
import com.geo.json.model.geometry.GeometryCollectionDto;
import com.geo.json.model.geometry.GeometryDto;
import com.geo.json.parser.util.BoundingBoxParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GeometryCollectionDeserializer implements JsonDeserializer<GeometryCollectionDto> {

	@Override
	public GeometryCollectionDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		GeometryCollectionDto dto = new GeometryCollectionDto();
		List<GeometryDto> geometries = new ArrayList<>();
		dto.setGeometries(geometries);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("geometries").getAsJsonArray();
		if (jsonArray == null) {
			return dto;
		}

		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject geometryElement = jsonArray.get(i).getAsJsonObject();
			String typeOfGeometry = geometryElement.get("type").getAsString();
			GeoJSONObjectTypeEnum typeEnum = GeoJSONObjectTypeEnum.valueOf(typeOfGeometry);

			GeometryDto geometryDto = context.deserialize(geometryElement, typeEnum.getDtoClass());
			geometries.add(geometryDto);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return dto;
	}

}
