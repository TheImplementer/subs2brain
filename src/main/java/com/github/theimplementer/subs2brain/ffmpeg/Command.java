package com.github.theimplementer.subs2brain.ffmpeg;

import java.util.List;

public class Command {

    private final List<CommandOption> options;
    private final String outputFile;

    Command(List<CommandOption> options, String outputFile) {
        this.options = options;
        this.outputFile = outputFile;
    }

    public List<CommandOption> getOptions() {
        return options;
    }

    public String getOutputFile() {
        return outputFile;
    }
}
