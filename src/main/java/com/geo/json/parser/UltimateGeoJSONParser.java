package com.geo.json.parser;

import com.geo.json.model.geometry.*;
import com.geo.json.parser.deserializer.*;
import com.google.gson.*;
import com.geo.json.model.GeoJSONObjectDto;
import com.geo.json.model.GeoJSONObjectTypeEnum;
import com.geo.json.model.PositionDto;
import com.geo.json.model.feature.FeatureCollectionDto;
import com.geo.json.model.feature.FeatureDto;

public class UltimateGeoJSONParser {

	private Gson gson;

	private static final UltimateGeoJSONParser INSTANCE = new UltimateGeoJSONParser();

	private UltimateGeoJSONParser() {
		initialize();
	}

	public static UltimateGeoJSONParser getInstance() {
		return INSTANCE;
	}

	public GeoJSONObjectDto parse(String geoJson) {
		if (geoJson == null) {
			return null;
		}
		JsonElement parsed = JsonParser.parseString(geoJson);
		JsonObject jsonObject = parsed.getAsJsonObject();
		String typeString = jsonObject.get("type").getAsString();
		GeoJSONObjectTypeEnum typeEnum = GeoJSONObjectTypeEnum.valueOf(typeString);
		return gson.fromJson(parsed, typeEnum.getDtoClass());
	}

	private void initialize() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(PointDto.class, new PointDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(PolygonDto.class, new PolygonDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPointDto.class, new MultiPointDeserializer());
		gsonBuilder.registerTypeAdapter(MultiLineStringDto.class, new MultiLineStringDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPolygonDto.class, new MultiPolygonDeserializer());
		gsonBuilder.registerTypeAdapter(GeometryCollectionDto.class, new GeometryCollectionDeserializer());
		gsonBuilder.registerTypeAdapter(FeatureDto.class, new FeatureDeserializer());
		gsonBuilder.registerTypeAdapter(FeatureCollectionDto.class, new FeatureCollectionDeserializer());
		gson = gsonBuilder.create();
	}

}
