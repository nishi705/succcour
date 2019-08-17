package com.stackroute.kafkaproducer;


//import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.lang.String;

@Configuration
@EnableKafka
public class KafkaProducerConfig {
    //producer configuration factory

    @Bean
    public DefaultKafkaProducerFactory<String,User> producerFactory() {
        //this is configuration for this bean and cofiguration is hashmap
        Map<String,Object> configProps = new HashMap<>();
        //this is server configuaration and localhost is server name
        //and default port is 9092
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //this is key
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //this is value
        //the below code will say to kafka that im using jsonserializer configuration
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    //kafka template
    //this bean will tell us that we are having above configuration
    @Bean
    public KafkaTemplate<String, User> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}
