package org.example.mcibigdatachallenge.controller;

import lombok.AllArgsConstructor;
import org.example.mcibigdatachallenge.dto.CityTemperatureDto;
import org.example.mcibigdatachallenge.dto.HumidCity;
import org.example.mcibigdatachallenge.model.EntityResponse;
import org.example.mcibigdatachallenge.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/top-humid-cities")
    public ResponseEntity<EntityResponse<?>> findTopHumidCities(){
        List<HumidCity> topHumidCities = weatherService.findTopHumidCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(topHumidCities).status("success").build());
    }

    @GetMapping("/hottest-cities/daily")
    public ResponseEntity<EntityResponse<?>> findDailyHottestCities(){
        List<CityTemperatureDto> cityTemperatureDtos = weatherService.findDailyHottestCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(cityTemperatureDtos).status("success").build());
    }

    @GetMapping("/hottest-cities/monthly")
    public ResponseEntity<EntityResponse<?>> findMonthlyHottestCities(){
        List<CityTemperatureDto> cityTemperatureDtos = weatherService.findMonthlyHottestCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(cityTemperatureDtos).status("success").build());
    }

    @GetMapping("/coldest-cities/daily")
    public ResponseEntity<EntityResponse<?>> findDailyColdestCities(){
        List<CityTemperatureDto> cityTemperatureDtos = weatherService.findDailyColdestCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(cityTemperatureDtos).status("success").build());
    }

    @GetMapping("/coldest-cities/monthly")
    public ResponseEntity<EntityResponse<?>> findMonthlyColdestCities(){
        List<CityTemperatureDto> cityTemperatureDtos = weatherService.findMonthlyColdestCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(cityTemperatureDtos).status("success").build());
    }
}
