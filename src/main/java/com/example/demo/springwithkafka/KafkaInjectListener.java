package com.example.demo.springwithkafka;

import com.example.demo.MyController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
//@Component
public class KafkaInjectListener {

    private final ObjectProvider<MyController> myControllerObjectProvider;
    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    @EventListener
    public void OnApplication(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("HERE");
        KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
        MyController myController = myControllerObjectProvider.getObject();
        myController.configKafkaStreams(kafkaStreams);
    }
}
