package com.example.integrationmultipleflow.adapter.api.file.flow;

import com.example.integrationmultipleflow.adapter.api.file.configuration.FilePollerConfigurationProperties;
import com.example.integrationmultipleflow.adapter.api.file.handler.FileAHandler;
import com.example.integrationmultipleflow.adapter.api.file.handler.FileBHandler;
import com.example.integrationmultipleflow.adapter.api.file.handler.FileCHandler;
import com.example.integrationmultipleflow.adapter.api.file.handler.FileHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.dsl.Files;

import java.io.File;

import static com.example.integrationmultipleflow.adapter.api.file.configuration.ChannelConfiguration.*;
import static com.example.integrationmultipleflow.adapter.api.file.flow.ErrorFlow.ERROR_CHANNEL;

@Configuration
@EnableConfigurationProperties(FilePollerConfigurationProperties.class)
public class IntegrationFlow {

    private static final String ARCHIVE_DESTINATION = "archive-destination-path";
    private final FilePollerConfigurationProperties filePollerConfigurationProperties;

    public IntegrationFlow(FilePollerConfigurationProperties filePollerConfigurationProperties) {
        this.filePollerConfigurationProperties = filePollerConfigurationProperties;
    }

    @Bean(name = "fileMessageSource")
    public FileReadingMessageSource fileMessageSource (FilePollerConfigurationProperties filePollerConfigurationProperties) {
        return Files.inboundAdapter(new File(filePollerConfigurationProperties.getInputPath())).get();
    }

    @Bean
    public StandardIntegrationFlow filePollerFlow(@Qualifier(value = "fileMessageSource")FileReadingMessageSource fileReadingMessageSource,
                                                  TaskExecutor taskExecutor,
                                                  FileHandler fileHandler) {
        return IntegrationFlows.from(fileReadingMessageSource, c -> c.poller(Pollers.fixedDelay(filePollerConfigurationProperties.getPollerFixedDelay())
                                                                                    .taskExecutor(taskExecutor).maxMessagesPerPoll(filePollerConfigurationProperties
                                                                                    .getMaxMessagePerPoll()))
                                                                    .id("filePollingId"))
                .enrichHeaders(h -> h.header(ARCHIVE_DESTINATION, filePollerConfigurationProperties.getArchivePath(), true))
                .channel(MAIN_CHANNEL)
                .handle(fileHandler)
                .get();
    }

    @Bean
    public StandardIntegrationFlow routeFlow(){
        var regexPattern = filePollerConfigurationProperties.getRegexPattern();
        return IntegrationFlows.from(MAIN_CHANNEL).routeToRecipients(route -> route
                .recipient(FILEA_CHANNEL, file -> ((File)file).getName().matches(regexPattern.getFileA()))
                .recipient(FILEB_CHANNEL, file -> ((File)file).getName().matches(regexPattern.getFileB()))
                .recipient(FILEC_CHANNEL, file -> ((File)file).getName().matches(regexPattern.getFileC()))
                .defaultOutputChannel(ERROR_CHANNEL)).get();
    }

    @Bean
    public StandardIntegrationFlow fileAHandlerFlow(FileAHandler fileAHandler){
        return IntegrationFlows.from(FILEA_CHANNEL).handle(fileAHandler).get();
    }

    @Bean
    public StandardIntegrationFlow fileBHandlerFlow(FileBHandler fileBHandler){
        return IntegrationFlows.from(FILEB_CHANNEL).handle(fileBHandler).get();
    }

    @Bean
    public StandardIntegrationFlow fileCHandlerFlow(FileCHandler fileCHandler){
        return IntegrationFlows.from(FILEC_CHANNEL).handle(fileCHandler).get();
    }
}
