package com.github.theimplementer.subs2brain.options;

public class ApplicationOptions {

    private final String subsFileLocation;
    private final String videoFileLocation;
    private final String outputDirectory;
    private final boolean extractAudio;

    public ApplicationOptions(String subsFileLocation, String videoFileLocation, String outputDirectory, boolean extractAudio) {
        this.subsFileLocation = subsFileLocation;
        this.videoFileLocation = videoFileLocation;
        this.outputDirectory = outputDirectory;
        this.extractAudio = extractAudio;
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

    public boolean isExtractAudio() {
        return extractAudio;
    }
}
