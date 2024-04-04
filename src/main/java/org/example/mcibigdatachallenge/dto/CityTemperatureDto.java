package org.example.mcibigdatachallenge.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CityTemperatureDto {
    private final String city;
    private final LocalDateTime date;
    private final Double apparentTemperature;
}
