package org.example.mcibigdatachallenge.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mcibigdatachallenge.dto.WeatherEventDto;
import org.example.mcibigdatachallenge.entity.WeatherEventEntity;
import org.example.mcibigdatachallenge.proto.WeatherEventOuterClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DeserializerUtil {
    private DeserializerUtil(){}
    private static final DateTimeFormatter[] FORMATTERS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
    };
    public static Optional<WeatherEventDto> jsonDeserializer(ObjectMapper objectMapper,String input){
        WeatherEventDto weatherEventDto = null;
        try {
            weatherEventDto = objectMapper.readValue(input, WeatherEventDto.class);
        } catch (JsonProcessingException e) {
            //ignore
        }
        return Optional.ofNullable(weatherEventDto);
    }

    public static Optional<WeatherEventEntity> protoDeserializer(WeatherEventOuterClass.WeatherEvent weatherEvent){
        WeatherEventEntity weatherEventEntity = new WeatherEventEntity();
        weatherEventEntity.setCity(weatherEvent.getCity());
        weatherEventEntity.setPrecipIntensity(weatherEvent.getCurrentlyPrecipIntensity());
        weatherEventEntity.setVisibility(weatherEvent.getCurrentlyVisibility());
        weatherEventEntity.setHumidity(weatherEvent.getCurrentlyHumidity());
        weatherEventEntity.setTemperature(weatherEvent.getCurrentlyTemperature());
        weatherEventEntity.setApparentTemperature(weatherEvent.getCurrentlyApparentTemperature());
        weatherEventEntity.setPrecipProbability(weatherEvent.getCurrentlyPrecipProbability());
        weatherEventEntity.setWindSpeed(weatherEvent.getCurrentlyWindSpeed());
        weatherEventEntity.setPrecipType(weatherEvent.getCurrentlyPrecipType().isEmpty()?"UNKNOWN":weatherEvent.getCurrentlyPrecipType());
        String dateTimeStr = weatherEvent.getDateTime();
        if(dateTimeStr.length()==19){
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, FORMATTERS[0]);
            weatherEventEntity.setDateTime(localDateTime);
        }else if (dateTimeStr.length()==10){
            LocalDateTime localDateTime = LocalDate.parse(dateTimeStr, FORMATTERS[1]).atStartOfDay();
            weatherEventEntity.setDateTime(localDateTime);
        }else {
            return Optional.empty();
        }
        return Optional.of(weatherEventEntity);
    }


    public static LocalDateTime convertToLocalDateTime(String dateStr){
        if(dateStr.length()==19){
            return LocalDateTime.parse(dateStr, FORMATTERS[0]);

        }else if (dateStr.length()==10){
            return LocalDate.parse(dateStr, FORMATTERS[1]).atStartOfDay();
        }else {
            return null;
        }
    }
}
