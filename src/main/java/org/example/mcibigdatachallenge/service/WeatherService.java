package org.example.mcibigdatachallenge.service;

import lombok.AllArgsConstructor;
import org.example.mcibigdatachallenge.dto.CityTemperatureDto;
import org.example.mcibigdatachallenge.dto.HumidCity;
import org.example.mcibigdatachallenge.repository.WeatherEventRepository;
import org.example.mcibigdatachallenge.util.DeserializerUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WeatherService {

    private final WeatherEventRepository weatherEventRepository;

    public List<HumidCity> findTopHumidCities() {
        List<HumidCity> humidCities = new ArrayList<>();
        List<Object[]> topHumidCities = weatherEventRepository.findTopHumidCities();
        for (Object[] topHumidCity : topHumidCities) {
            humidCities.add(HumidCity.builder()
                    .date(DeserializerUtil.convertToLocalDateTime(topHumidCity[0].toString()))
                    .city((String) topHumidCity[1])
                    .averageHumidity((Double) topHumidCity[2])
                    .build());

        }
        return humidCities;
    }

    public List<CityTemperatureDto> findDailyHottestCities() {
        List<CityTemperatureDto> cityTemperatureDtos = new ArrayList<>();
        List<Object[]> dailyHottestCities = weatherEventRepository.findDailyHottestCities();
        for (Object[] dailyHottestCity : dailyHottestCities) {
            cityTemperatureDtos.add(CityTemperatureDto.builder()
                    .date(DeserializerUtil.convertToLocalDateTime(dailyHottestCity[0].toString()))
                    .city((String) dailyHottestCity[1])
                    .apparentTemperature((Double) dailyHottestCity[2])
                    .build());
        }
        return cityTemperatureDtos;
    }

    public List<CityTemperatureDto> findMonthlyHottestCities() {
        List<CityTemperatureDto> cityTemperatureDtos = new ArrayList<>();
        List<Object[]> monthlyHottestCities = weatherEventRepository.findMonthlyHottestCities();
        for (Object[] monthlyHottestCity : monthlyHottestCities) {
            cityTemperatureDtos.add(CityTemperatureDto.builder()
                    .date(DeserializerUtil.convertToLocalDateTime(monthlyHottestCity[0].toString()))
                    .city((String) monthlyHottestCity[1])
                    .apparentTemperature((Double) monthlyHottestCity[2])
                    .build());
        }
        return cityTemperatureDtos;
    }

    public List<CityTemperatureDto> findDailyColdestCities() {
        List<CityTemperatureDto> cityTemperatureDtos = new ArrayList<>();
        List<Object[]> dailyColdestCities = weatherEventRepository.findDailyColdestCities();
        for (Object[] dailyColdestCity : dailyColdestCities) {
            cityTemperatureDtos.add(CityTemperatureDto.builder()
                    .date(DeserializerUtil.convertToLocalDateTime(dailyColdestCity[0].toString()))
                    .city((String) dailyColdestCity[1])
                    .apparentTemperature((Double) dailyColdestCity[2])
                    .build());
        }
        return cityTemperatureDtos;
    }

    public List<CityTemperatureDto> findMonthlyColdestCities() {
        List<CityTemperatureDto> cityTemperatureDtos = new ArrayList<>();
        List<Object[]> monthlyColdestCities = weatherEventRepository.findMonthlyColdestCities();
        for (Object[] monthlyColdestCity : monthlyColdestCities) {
            cityTemperatureDtos.add(CityTemperatureDto.builder()
                    .date(DeserializerUtil.convertToLocalDateTime(monthlyColdestCity[0].toString()))
                    .city((String) monthlyColdestCity[1])
                    .apparentTemperature((Double) monthlyColdestCity[2])
                    .build());
        }
        return cityTemperatureDtos;
    }
}
