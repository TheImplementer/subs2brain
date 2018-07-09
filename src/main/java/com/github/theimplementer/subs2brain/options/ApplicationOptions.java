package com.github.theimplementer.subs2brain.options;

import java.io.File;

public class ApplicationOptions {

    private final File subsFile;
    private final File videoFile;
    private final File outputDirectory;

    public ApplicationOptions(File subsFile, File videoFile, File outputDirectory) {
        this.subsFile = subsFile;
        this.videoFile = videoFile;
        this.outputDirectory = outputDirectory;
    }

    public File getSubsFile() {
        return subsFile;
    }

    public File getVideoFile() {
        return videoFile;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }
}
