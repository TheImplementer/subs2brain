package com.github.theimplementer.subs2brain.ffmpeg;

import java.util.List;

public class Command {

    private final List<CommandOption> inputOptions;
    private final String inputFile;
    private final List<CommandOption> outputOptions;
    private final String outputFile;

    Command(List<CommandOption> inputOptions, String inputFile, List<CommandOption> outputOptions, String outputFile) {
        this.inputOptions = inputOptions;
        this.inputFile = inputFile;
        this.outputOptions = outputOptions;
        this.outputFile = outputFile;
    }

    public List<CommandOption> getInputOptions() {
        return inputOptions;
    }

    public String getInputFile() {
        return inputFile;
    }

    public List<CommandOption> getOutputOptions() {
        return outputOptions;
    }

    public String getOutputFile() {
        return outputFile;
    }
}
