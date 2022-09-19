package com.example.integrationmultipleflow.adapter.api.file.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file-poller")
public class FilePollerConfigurationProperties {

    private Long pollerFixedDelay;
    private Integer maxMessagePerPoll;
    private String archivePath;
    private String errorPath;
    private String inputPath;
    private Integer maxAttempts;
    private Integer retryDelayInSeconds;
    private Integer timeoutForFileSizeCheck;
    private Integer threadPoolSize;
    private RegexPatternConfigurationProperties regexPattern;

    public Long getPollerFixedDelay() {
        return pollerFixedDelay;
    }

    public void setPollerFixedDelay(Long pollerFixedDelay) {
        this.pollerFixedDelay = pollerFixedDelay;
    }

    public Integer getMaxMessagePerPoll() {
        return maxMessagePerPoll;
    }

    public void setMaxMessagePerPoll(Integer maxMessagePerPoll) {
        this.maxMessagePerPoll = maxMessagePerPoll;
    }

    public String getArchivePath() {
        return archivePath;
    }

    public void setArchivePath(String archivePath) {
        this.archivePath = archivePath;
    }

    public String getErrorPath() {
        return errorPath;
    }

    public void setErrorPath(String errorPath) {
        this.errorPath = errorPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public Integer getRetryDelayInSeconds() {
        return retryDelayInSeconds;
    }

    public void setRetryDelayInSeconds(Integer retryDelayInSeconds) {
        this.retryDelayInSeconds = retryDelayInSeconds;
    }

    public Integer getTimeoutForFileSizeCheck() {
        return timeoutForFileSizeCheck;
    }

    public void setTimeoutForFileSizeCheck(Integer timeoutForFileSizeCheck) {
        this.timeoutForFileSizeCheck = timeoutForFileSizeCheck;
    }

    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public RegexPatternConfigurationProperties getRegexPattern() {
        return regexPattern;
    }

    public void setRegexPattern(RegexPatternConfigurationProperties regexPattern) {
        this.regexPattern = regexPattern;
    }
}
