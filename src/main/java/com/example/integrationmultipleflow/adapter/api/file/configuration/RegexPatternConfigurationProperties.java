package com.example.integrationmultipleflow.adapter.api.file.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "regex-pattern")
public class RegexPatternConfigurationProperties {

    private String fileA;
    private String fileB;
    private String fileC;

    public String getFileA() {
        return fileA;
    }

    public void setFileA(String fileA) {
        this.fileA = fileA;
    }

    public String getFileB() {
        return fileB;
    }

    public void setFileB(String fileB) {
        this.fileB = fileB;
    }

    public String getFileC() {
        return fileC;
    }

    public void setFileC(String fileC) {
        this.fileC = fileC;
    }
}
