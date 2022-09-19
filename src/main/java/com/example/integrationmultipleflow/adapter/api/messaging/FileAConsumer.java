package com.example.integrationmultipleflow.adapter.api.messaging;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("fileAConsumer")
public class FileAConsumer extends AbstractMessageConsumer<String> {

    @Override
    protected void handleMessage(Message<String> message) {
        System.out.println("Hello I'm consumer A and I handle message");
    }
}
