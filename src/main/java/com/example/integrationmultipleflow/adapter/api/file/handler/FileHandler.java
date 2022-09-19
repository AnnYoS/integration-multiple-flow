package com.example.integrationmultipleflow.adapter.api.file.handler;

import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileHandler implements MessageHandler {

    private static final String ARCHIVE_DESTINATION = "archive-destination-path";
    private static final String ERROR_DESTINATION = "error-destination-path";

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        var filename = (String) message.getHeaders().get(FileHeaders.FILENAME);
        var archiveDest = (String) message.getHeaders().get(ARCHIVE_DESTINATION);
        var errorDest = (String) message.getHeaders().get(ERROR_DESTINATION);
        var file = (File) message.getHeaders().get(FileHeaders.ORIGINAL_FILE);
        if (errorDest != null && file != null && filename != null) {
            try {
                Path path = Paths.get(errorDest);
                Files.createDirectories(path);
            } catch (IOException e) {
                // better log you have
                System.out.println("Error when creating error folder");
            }
        } else if (archiveDest != null && file != null && filename != null) {
            try {
                Path path = Paths.get(archiveDest);
                Files.createDirectories(path);
            } catch (IOException e) {
                // better log you have
                System.out.println("Error when creating archive folder");
            }
        }
    }

    private void moveFileIntoFolder(String source, String target) {
        try{
            Files.move(Paths.get(source), Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // better log you have
            System.out.println("Error when archive the file in correct folder");
        }
    }
}
