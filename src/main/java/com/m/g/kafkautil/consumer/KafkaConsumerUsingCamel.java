package com.m.g.kafkautil.consumer;


import com.m.g.kafkautil.consumer.processor.KafkaCamelProcessor;
import org.apache.camel.builder.ProcessClause;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerUsingCamel extends RouteBuilder {


    @Autowired
    KafkaCamelProcessor kafkaCamelProcessor;

    @Override
    public void configure() throws Exception {
        String kafkaURI = "kafka:javainuse-topic?brokers=localhost:9092";

        from(kafkaURI).
                routeId("SEDA_1").autoStartup(true).
                to("seda:tempTopic?size=1000").
                end();

        from("seda:tempTopic").
                routeId("FROM_SEDA").autoStartup(true).
                process(kafkaCamelProcessor).end();
    }
}
