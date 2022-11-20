package com.example.demo.springwithkafka;

import com.example.demo.MyController;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Component;

import static org.apache.kafka.streams.Topology.AutoOffsetReset.EARLIEST;

//@Component
public class BuildPipeline {

    @Autowired
    public void buildPipeline(StreamsBuilder streamsBuilder, MyController myController) {
        streamsBuilder.stream("clients",
                        Consumed.with(Serdes.String(), Serdes.String()).withOffsetResetPolicy(EARLIEST))
                .print(Printed.<String, String>toSysOut().withLabel("HELLO"));
    }
}
