package com.github.theimplementer.subs2brain.options;

public class ApplicationOptions {

    private final String subsFileLocation;
    private final String videoFileLocation;
    private final String outputDirectory;
    private final String outputFilesPrefix;
    private final boolean extractAudio;
    private final boolean extractScreenshots;

    public ApplicationOptions(
            String subsFileLocation,
            String videoFileLocation,
            String outputDirectory,
            String outputFilesPrefix,
            boolean extractAudio,
            boolean extractScreenshots) {
        this.subsFileLocation = subsFileLocation;
        this.videoFileLocation = videoFileLocation;
        this.outputDirectory = outputDirectory;
        this.outputFilesPrefix = outputFilesPrefix;
        this.extractAudio = extractAudio;
        this.extractScreenshots = extractScreenshots;
    }

    public String getSubsFileLocation() {
        return subsFileLocation;
    }

    public String getVideoFileLocation() {
        return videoFileLocation;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public String getOutputFilesPrefix() {
        return outputFilesPrefix;
    }

    public boolean extractAudio() {
        return extractAudio;
    }

    public boolean extractScreenshots() {
        return extractScreenshots;
    }
}
