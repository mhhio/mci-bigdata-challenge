package org.example.mcibigdatachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherEventDto {
    @JsonProperty("weather_data.date_time")
    private String dateTime;
    @JsonProperty("weather_data.city")
    private String city;
    @JsonProperty("weather_data.currently_apparenttemperature")
    private Double currentlyApparentTemperature;
    @JsonProperty("weather_data.currently_humidity")
    private Double currentlyHumidity;
    @JsonProperty("weather_data.currently_precipintensity")
    private Double currentlyPrecipIntensity;
    @JsonProperty("weather_data.currently_precipprobability")
    private Double currentlyPrecipProbability;
    @JsonProperty("weather_data.currently_preciptype")
    private String currentlyPrecipType;
    @JsonProperty("weather_data.currently_temperature")
    private Double currentlyTemperature;
    @JsonProperty("weather_data.currently_visibility")
    private Double currentlyVisibility;
    @JsonProperty("weather_data.currently_windspeed")
    private Double currentlyWindSpeed;
}
