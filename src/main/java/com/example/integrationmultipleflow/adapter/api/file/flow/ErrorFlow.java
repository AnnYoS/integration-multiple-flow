package com.example.integrationmultipleflow.adapter.api.file.flow;

import com.example.integrationmultipleflow.adapter.api.file.configuration.FilePollerConfigurationProperties;
import com.example.integrationmultipleflow.adapter.api.file.handler.FileHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ErrorFlow {

    private static final String ERROR_DESTINATION = "error-destination-path";
    public static final String ERROR_CHANNEL = "error-channel";

    @Bean(name = "errorFlow")
    public StandardIntegrationFlow errorFlow(FileHandler fileHandler, FilePollerConfigurationProperties filePollerConfigurationProperties) {
        return IntegrationFlows.from(ERROR_CHANNEL)
                .enrichHeaders(h -> h.header(ERROR_DESTINATION, filePollerConfigurationProperties.getErrorPath(), true))
                .handle(fileHandler, g -> g.id("errorHandlerId")).get();
    }

    @Bean(name = ERROR_CHANNEL)
    public MessageChannel errorChannel() {
        return new DirectChannel();
    }
}
