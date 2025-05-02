package com.geo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapDetailsDto {

    private List<Integer> orderedYears;
    private List<LayerDetailsDto> layers;
    private List<LayerDataDto> layersData;

}
