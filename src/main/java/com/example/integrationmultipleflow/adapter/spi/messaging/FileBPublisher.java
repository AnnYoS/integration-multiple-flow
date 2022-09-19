package com.example.integrationmultipleflow.adapter.spi.messaging;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component("fileBPublisher")
public class FileBPublisher extends KafkaPublisher<String>{

    protected FileBPublisher(StreamBridge streamBridge) {
        super(streamBridge);
    }

    @Override
    protected String getBindingName() {
        return "fileAPublisher-out-0";
    }

    @Override
    public void publish(String object, String eventType) {
        final HashMap<String, Object> headers = getBasicHeaders();
        headers.put("event_type", eventType);
        headers.put("schema_type", object.getClass().getName());
        Message<String> message = MessageBuilder.withPayload(object).copyHeaders(headers).build();
        streamBridge.send(getBindingName(), message);
    }
}
