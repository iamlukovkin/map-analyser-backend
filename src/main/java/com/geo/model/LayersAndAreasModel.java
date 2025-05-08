package com.geo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LayersAndAreasModel {
    @JsonProperty("layers")
    private List<LayerMapModel> layers;
    @JsonProperty("areas")
    private HashMap<String, HashMap<Long, HashMap<Integer, Double>>> areas;
}
