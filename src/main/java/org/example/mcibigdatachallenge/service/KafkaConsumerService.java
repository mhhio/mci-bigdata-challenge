package org.example.mcibigdatachallenge.service;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.example.mcibigdatachallenge.entity.WeatherEventEntity;
import org.example.mcibigdatachallenge.proto.WeatherEventOuterClass;
import org.example.mcibigdatachallenge.repository.WeatherEventRepository;
import org.example.mcibigdatachallenge.util.DeserializerUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class KafkaConsumerService {
    private final WeatherEventRepository weatherEventRepository;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}",autoStartup = "${listen.auto.start:true}")
    public void consume(ConsumerRecord<String, byte[]> record) {
        try {
            WeatherEventOuterClass.WeatherEvent weatherEvent = WeatherEventOuterClass.WeatherEvent.parseFrom(record.value());
            Optional<WeatherEventEntity> weatherEventEntityOptional = DeserializerUtil.protoDeserializer(weatherEvent);
            if (weatherEventEntityOptional.isPresent()) {
                WeatherEventEntity weatherEventEntity = weatherEventEntityOptional.get();
                //ignore invalid city name event
                if(weatherEventEntity.getCity().equals("test")){
                    return;
                }
                weatherEventRepository.save(weatherEventEntityOptional.get());
            }
        } catch (Exception e) {
            //ignore record
        }
    }
}
