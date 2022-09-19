package com.example.integrationmultipleflow.adapter.api.file.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelConfiguration {

    public static final String MAIN_CHANNEL = "main-channel";
    public static final String FILEA_CHANNEL = "fileA-channel";
    public static final String FILEB_CHANNEL = "fileB-channel";
    public static final String FILEC_CHANNEL = "fileC-channel";

    @Bean(name = MAIN_CHANNEL)
    public MessageChannel mainChannel(){
        return new DirectChannel();
    }

    @Bean(name = FILEA_CHANNEL)
    public MessageChannel fileAChannel(){
        return new DirectChannel();
    }

    @Bean(name = FILEB_CHANNEL)
    public MessageChannel fileBChannel(){
        return new DirectChannel();
    }

    @Bean(name = FILEC_CHANNEL)
    public MessageChannel fileCChannel(){
        return new DirectChannel();
    }
}
