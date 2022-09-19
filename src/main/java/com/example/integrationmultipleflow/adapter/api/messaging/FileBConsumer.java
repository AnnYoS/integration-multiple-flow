package com.example.integrationmultipleflow.adapter.api.messaging;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component("fileBConsumer")
public class FileBConsumer extends AbstractMessageConsumer<String>{

    @Override
    protected void handleMessage(Message<String> message) {
        System.out.println("Hello I'm consumer B and I handle message");
    }
}
