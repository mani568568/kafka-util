package com.m.g.kafkautil.consumer.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class KafkaCamelProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        String body = (String) exchange.getIn().getBody();

        System.out.println(body);
    }
}
