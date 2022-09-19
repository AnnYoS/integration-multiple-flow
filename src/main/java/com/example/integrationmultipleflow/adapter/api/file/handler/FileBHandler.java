package com.example.integrationmultipleflow.adapter.api.file.handler;

import com.example.integrationmultipleflow.adapter.spi.messaging.KafkaPublisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FileBHandler extends AbstractHandler{

    private final KafkaPublisher<String> publisher;

    public FileBHandler(@Qualifier(value = "fileBPublisher") KafkaPublisher<String> publisher) {
        this.publisher = publisher;
    }

    @Override
    protected void publish(String payload) {
        publisher.publish(payload, "string.new");
    }
}
