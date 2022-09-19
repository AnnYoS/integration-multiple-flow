package com.example.integrationmultipleflow.adapter.spi.messaging;

import org.springframework.cloud.stream.function.StreamBridge;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

public abstract class KafkaPublisher<T> {

    protected final StreamBridge streamBridge;

    protected KafkaPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    protected HashMap<String, Object> getBasicHeaders() {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("producer_id", "my-application");
        headers.put("create_timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        headers.put("correlation_id", UUID.randomUUID().toString());
        return headers;
    }

    protected abstract String getBindingName();

    public abstract void publish (T object, String eventType);
}
