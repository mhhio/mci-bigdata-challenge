package org.example.mcibigdatachallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.mcibigdatachallenge.dto.WeatherEventDto;
import org.example.mcibigdatachallenge.proto.WeatherEventOuterClass;
import org.example.mcibigdatachallenge.util.DeserializerUtil;
import org.example.mcibigdatachallenge.util.FileUtil;
import org.example.mcibigdatachallenge.util.SerializerUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;

    public void publish(String path) {
        //read all json string line
        List<String> lines = FileUtil.readLines(path);
        for (String line : lines) {
            //convert string to json
            Optional<WeatherEventDto> weatherEventDtoOptional = DeserializerUtil.jsonDeserializer(objectMapper, line);
            if (weatherEventDtoOptional.isPresent()) {
                WeatherEventDto weatherEventDto = weatherEventDtoOptional.get();
                if (weatherEventDto != null) {
                    //convert json to protobuf
                    WeatherEventOuterClass.WeatherEvent weatherEvent = SerializerUtil.createWeatherEventProto(weatherEventDto);
                    kafkaTemplate.send(topic, weatherEvent.toByteArray());
                }
            }

        }
    }

}
