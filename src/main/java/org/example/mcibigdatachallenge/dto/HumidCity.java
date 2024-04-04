package org.example.mcibigdatachallenge.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HumidCity {
    private String city;
    private LocalDateTime date;
    private Double averageHumidity;
}
