package com.example.integrationmultipleflow.adapter.api.file.handler;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class AbstractHandler implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        var payload = (File) message.getPayload();
        try {
            publish(Files.readString(payload.toPath()));
        } catch (IOException e) {
            System.out.println("Impossible to publish into internal topic");
        }
    }

    protected abstract void publish(String payload);
}
