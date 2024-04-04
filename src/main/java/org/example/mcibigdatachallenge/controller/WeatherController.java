package org.example.mcibigdatachallenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.example.mcibigdatachallenge.dto.CityTemperatureDto;
import org.example.mcibigdatachallenge.dto.HumidCity;
import org.example.mcibigdatachallenge.model.BaseResponse;
import org.example.mcibigdatachallenge.model.CityTemperatureListEntityResponse;
import org.example.mcibigdatachallenge.model.EntityResponse;
import org.example.mcibigdatachallenge.model.HumidCityEntityResponse;
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
    @Operation(summary = "Show top humid cities for each day", description = "This will be return list of HumidCity objects based on sql native query",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = HumidCityEntityResponse.class)))
            })
    public ResponseEntity<EntityResponse<?>> findTopHumidCities(){
        List<HumidCity> topHumidCities = weatherService.findTopHumidCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(topHumidCities).status("success").build());
    }

    @GetMapping("/hottest-cities/daily")
    @Operation(summary = "Show top daily hottest city for each day", description = "This will be return list of CityTemperatureDto objects based on sql native query",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = CityTemperatureListEntityResponse.class)))
            })
    public ResponseEntity<EntityResponse<?>> findDailyHottestCities(){
        List<CityTemperatureDto> cityTemperatureDtos = weatherService.findDailyHottestCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(cityTemperatureDtos).status("success").build());
    }

    @GetMapping("/hottest-cities/monthly")
    @Operation(summary = "Show top monthly hottest city for each day", description = "This will be return list of CityTemperatureDto objects based on sql native query",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = CityTemperatureListEntityResponse.class)))
            })
    public ResponseEntity<EntityResponse<?>> findMonthlyHottestCities(){
        List<CityTemperatureDto> cityTemperatureDtos = weatherService.findMonthlyHottestCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(cityTemperatureDtos).status("success").build());
    }

    @GetMapping("/coldest-cities/daily")
    @Operation(summary = "Show top daily coldest city for each day", description = "This will be return list of CityTemperatureDto objects based on sql native query",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = CityTemperatureListEntityResponse.class)))
            })
    public ResponseEntity<EntityResponse<?>> findDailyColdestCities(){
        List<CityTemperatureDto> cityTemperatureDtos = weatherService.findDailyColdestCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(cityTemperatureDtos).status("success").build());
    }

    @GetMapping("/coldest-cities/monthly")
    @Operation(summary = "Show top monthly coldest city for each day", description = "This will be return list of CityTemperatureDto objects based on sql native query",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = CityTemperatureListEntityResponse.class)))
            })
    public ResponseEntity<EntityResponse<?>> findMonthlyColdestCities(){
        List<CityTemperatureDto> cityTemperatureDtos = weatherService.findMonthlyColdestCities();
        return ResponseEntity.ok(EntityResponse.entityResponseBuilder().entity(cityTemperatureDtos).status("success").build());
    }
}
