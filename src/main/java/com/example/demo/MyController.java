package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KafkaStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyController {

    private final KafkaStreams kafkaStreams;

    /*
    public void configKafkaStreams(KafkaStreams kafkaStreams) {
        this.kafkaStreams = kafkaStreams;
    }

    @Autowired(required = false)
    public MyController(KafkaStreams kafkaStreams) {
        this.kafkaStreams = kafkaStreams;
    }

     */

    @GetMapping("/hello")
    public String hello() {
        System.out.println("kafkaStreams = " + kafkaStreams);
        return kafkaStreams.toString();
    }
}
