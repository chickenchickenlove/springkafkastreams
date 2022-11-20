package com.example.demo.mycomponent;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

import static org.apache.kafka.streams.Topology.AutoOffsetReset.EARLIEST;

@Component
public class MyKafkaStreams {

    @Bean
    public KafkaStreams kafkaStreams() {
        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "HELLO");
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        streamsBuilder.stream("clients",
                Consumed.with(Serdes.String(), Serdes.String()).withOffsetResetPolicy(EARLIEST))
                .print(Printed.<String, String>toSysOut().withLabel("HELLO"));

        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);
        kafkaStreams.start();
        return kafkaStreams;
    }
}
