package com.github.theimplementer.subs2brain.options;

public class ApplicationOptions {

    private final String subsFile;
    private final String videoFile;
    private final String outputDirectory;
    private final boolean extractAudio;

    public ApplicationOptions(String subsFile, String videoFile, String outputDirectory, boolean extractAudio) {
        this.subsFile = subsFile;
        this.videoFile = videoFile;
        this.outputDirectory = outputDirectory;
        this.extractAudio = extractAudio;
    }

    public String getSubsFile() {
        return subsFile;
    }

    public String getVideoFile() {
        return videoFile;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public boolean isExtractAudio() {
        return extractAudio;
    }
}
