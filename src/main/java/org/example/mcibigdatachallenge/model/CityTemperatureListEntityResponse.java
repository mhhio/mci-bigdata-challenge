package org.example.mcibigdatachallenge.model;

import org.example.mcibigdatachallenge.dto.CityTemperatureDto;

import java.util.List;

public class CityTemperatureListEntityResponse extends EntityResponse<List<CityTemperatureDto>> {
    public CityTemperatureListEntityResponse(List<CityTemperatureDto> entity, String status) {
        super(entity, status);
    }
}
