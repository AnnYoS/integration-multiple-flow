package com.example.integrationmultipleflow.adapter.api.messaging;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("fileCConsumer")
public class FileCConsumer extends AbstractMessageConsumer<String>{

    @Override
    protected void handleMessage(Message<String> message) {
        System.out.println("Hello I'm consumer  and I handle message");
    }
}
