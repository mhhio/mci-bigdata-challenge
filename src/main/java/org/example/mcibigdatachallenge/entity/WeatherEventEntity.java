package org.example.mcibigdatachallenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "weather_event")
@Getter
@Setter
public class WeatherEventEntity {
    @Id
    @SequenceGenerator(name="weather_event_seq_gen", sequenceName="weather_event_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="weather_event_seq_gen")
    private Long id;
    private LocalDateTime dateTime;
    private String city;
    private Double apparentTemperature;
    private Double humidity;
    private Double precipIntensity;
    private Double precipProbability;
    private String precipType;
    private Double temperature;
    private Double visibility;
    private Double windSpeed;
}
