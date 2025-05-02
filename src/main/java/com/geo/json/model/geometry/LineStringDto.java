package com.geo.json.model.geometry;

import lombok.Getter;
import lombok.Setter;
import com.geo.json.model.GeoJSONObjectTypeEnum;
import com.geo.json.model.PositionDto;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class LineStringDto extends GeometryDto {

	@Serial
	private static final long serialVersionUID = 1L;

	private List<PositionDto> positions;

	public LineStringDto(){
	}
	
	public LineStringDto(List<PositionDto> positions){
		this.positions = new ArrayList<>();
		for (PositionDto positionDto : positions) {
			this.positions.add(new PositionDto(positionDto));
		}
	}

    @Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.LineString;
	}
}
