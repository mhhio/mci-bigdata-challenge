package org.example.mcibigdatachallenge.repository;

import org.example.mcibigdatachallenge.entity.WeatherEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherEventRepository extends JpaRepository<WeatherEventEntity, Long> {
    @Query(value = "WITH daily_humidity AS (" +
            "  SELECT " +
            "    DATE(date_time) AS date, " +
            "    city, " +
            "    AVG(humidity) AS avg_humidity " +
            "  FROM weather_event " +
            "  GROUP BY date, city" +
            "), " +
            "ranked_cities AS (" +
            "  SELECT " +
            "    date, " +
            "    city, " +
            "    avg_humidity, " +
            "    RANK() OVER (PARTITION BY date ORDER BY avg_humidity DESC) as humidity_rank " +
            "  FROM daily_humidity" +
            ") " +
            "SELECT date, city, avg_humidity " +
            "FROM ranked_cities " +
            "WHERE humidity_rank = 1" +
            " ORDER BY date ASC", nativeQuery = true)
    List<Object[]> findTopHumidCities();

    @Query(value = "WITH daily_max_temp AS (" +
            "    SELECT" +
            "        date(date_time) AS day," +
            "        city," +
            "        apparent_temperature," +
            "        RANK() OVER (PARTITION BY date(date_time) ORDER BY apparent_temperature DESC) as temp_rank" +
            "    FROM weather_event" +
            ")" +
            "SELECT day, city, apparent_temperature" +
            " FROM daily_max_temp" +
            " WHERE temp_rank = 1", nativeQuery = true)
    List<Object[]> findDailyHottestCities();

    @Query(value = "WITH monthly_max_temp AS (" +
            "    SELECT" +
            "        date_trunc('month', date_time) AS month," +
            "        city," +
            "        apparent_temperature," +
            "        RANK() OVER (PARTITION BY date_trunc('month', date_time) ORDER BY apparent_temperature DESC) as temp_rank" +
            "    FROM weather_event" +
            ")" +
            "SELECT month, city, apparent_temperature" +
            " FROM monthly_max_temp" +
            " WHERE temp_rank = 1", nativeQuery = true)
    List<Object[]> findMonthlyHottestCities();

    @Query(value = "WITH daily_min_temp AS (" +
            "    SELECT" +
            "        date(date_time) AS day," +
            "        city," +
            "        apparent_temperature," +
            "        RANK() OVER (PARTITION BY date(date_time) ORDER BY apparent_temperature) as temp_rank" +
            "    FROM weather_event" +
            ")" +
            "SELECT day, city, apparent_temperature" +
            " FROM daily_min_temp" +
            " WHERE temp_rank = 1", nativeQuery = true)
    List<Object[]> findDailyColdestCities();


    @Query(value = "WITH monthly_min_temp AS (" +
            "    SELECT" +
            "        date_trunc('month', date_time) AS month," +
            "        city," +
            "        apparent_temperature," +
            "        RANK() OVER (PARTITION BY date_trunc('month', date_time) ORDER BY apparent_temperature) as temp_rank" +
            "    FROM weather_event" +
            ")" +
            "SELECT month, city, apparent_temperature" +
            " FROM monthly_min_temp" +
            " WHERE temp_rank = 1", nativeQuery = true)
    List<Object[]> findMonthlyColdestCities();


}
