package com.geo.mapper;

import com.geo.model.H3YearlyModel;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.function.Function;

@Service
public class H3YearlyModelMapper implements Function<H3YearlyModel, HashMap<Long, HashMap<Integer, Double>>> {

    @Setter
    private HashMap<String, HashMap<Long, HashMap<Integer, Double>>> sourceMap;

    @Override
    public HashMap<Long, HashMap<Integer, Double>> apply(H3YearlyModel relation) {
        HashMap<Long, HashMap<Integer, Double>> h3Map;
        if (sourceMap.containsKey(relation.getH3())) {
            h3Map = sourceMap.get(relation.getH3());
        } else {
            h3Map = new HashMap<>();
            sourceMap.put(relation.getH3(), h3Map);
        }
        HashMap<Integer, Double> featureMap;
        if (h3Map.containsKey(relation.getFeatureId())) {
            featureMap = h3Map.get(relation.getFeatureId());
        }  else {
            featureMap = new HashMap<>();
            h3Map.put(relation.getFeatureId(), featureMap);
        }
        featureMap.put(relation.getYear(), relation.getFeatureMeasurement());
        return h3Map;
    }
}
