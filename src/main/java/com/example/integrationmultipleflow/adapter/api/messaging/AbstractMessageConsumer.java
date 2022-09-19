package com.example.integrationmultipleflow.adapter.api.messaging;

import org.springframework.messaging.Message;

import java.util.function.Consumer;

public abstract class AbstractMessageConsumer<T> implements Consumer<Message<T>> {

    @Override
    public void accept(Message<T> message) {
        String correlationId = (String) message.getHeaders().get("correlation_id");
        System.out.println("I have consume message with correlation id: " + correlationId);
        handleMessage(message);
    }

    protected abstract void handleMessage(Message<T> message);
}
