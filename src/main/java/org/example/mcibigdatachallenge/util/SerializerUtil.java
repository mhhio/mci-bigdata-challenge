package org.example.mcibigdatachallenge.util;

import org.example.mcibigdatachallenge.dto.WeatherEventDto;
import org.example.mcibigdatachallenge.proto.WeatherEventOuterClass;

public class SerializerUtil {
    private SerializerUtil(){}

    public static WeatherEventOuterClass.WeatherEvent createWeatherEventProto(WeatherEventDto weatherEventDto){
        return WeatherEventOuterClass.WeatherEvent.newBuilder()
                .setDateTime(weatherEventDto.getDateTime())
                .setCity(weatherEventDto.getCity())
                .setCurrentlyApparentTemperature(weatherEventDto.getCurrentlyApparentTemperature())
                .setCurrentlyHumidity(weatherEventDto.getCurrentlyHumidity())
                .setCurrentlyPrecipIntensity(weatherEventDto.getCurrentlyPrecipIntensity()) // Casting due to Java's type system
                .setCurrentlyPrecipProbability(weatherEventDto.getCurrentlyPrecipProbability())
                .setCurrentlyPrecipType(weatherEventDto.getCurrentlyPrecipType() != null ? weatherEventDto.getCurrentlyPrecipType() : "")
                .setCurrentlyTemperature(weatherEventDto.getCurrentlyTemperature())
                .setCurrentlyVisibility(weatherEventDto.getCurrentlyVisibility())
                .setCurrentlyWindSpeed(weatherEventDto.getCurrentlyWindSpeed())
                .build();
    }

}
