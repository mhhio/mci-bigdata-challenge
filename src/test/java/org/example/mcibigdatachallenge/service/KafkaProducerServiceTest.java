package org.example.mcibigdatachallenge.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Testcontainers
//@EmbeddedKafka(partitions = 1, topics = {"weather_test"},brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class KafkaProducerServiceTest {

    @Container
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private ObjectMapper objectMapper;

//    @MockBean
//    private AppConfig appConfig;

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;

    KafkaProducerServiceTest() {
    }


    @Test
    public void testPublish() throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources","data","weather.json");
        String dataAbsolutePath = resourceDirectory.toFile().getAbsolutePath();
        // Mock the AppConfig to return the test topic name
//        when(appConfig.getTopic()).thenReturn("weather_test");
//        when(appConfig.getSourcePath()).thenReturn(dataAbsolutePath);

        // consumer config
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
        ConsumerFactory<String, byte[]> cf = new DefaultKafkaConsumerFactory<>(props);
        Consumer<String, byte[]> consumerTest = cf.createConsumer();
        consumerTest.subscribe(Collections.singleton("weather_test"));

        // Call the publish method
//        kafkaProducerService.publish();

        // consume records
        ConsumerRecords<String, byte[]> records = KafkaTestUtils.getRecords(consumerTest);
        ConsumerRecord<String, byte[]> record = records.iterator().next();
        assertThat(record).isNotNull();

        consumerTest.close();
    }
}