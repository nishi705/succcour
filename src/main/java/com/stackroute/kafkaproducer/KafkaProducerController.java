package com.stackroute.kafkaproducer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class KafkaProducerController {
//in place  of String we used her user model
//by default kafka uses the string serializer so no need to change return value
    @Autowired
    private KafkaTemplate<String, User> KafkaTemplate;
    private static final String TOPIC = "kafka_example";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {
        KafkaTemplate.send(TOPIC, new User(name, 22));
        return "published successfully";


    }

}

