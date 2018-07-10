package com.github.theimplementer.subs2brain.ffmpeg;

import java.util.ArrayList;
import java.util.List;

import static com.github.theimplementer.subs2brain.ffmpeg.SimpleOption.simpleOption;

public class CommandBuilder {

    private List<CommandOption> inputOptions = new ArrayList<>();
    private String inputFile;
    private List<CommandOption> outputOptions = new ArrayList<>();
    private String outputFile;

    public static CommandBuilder extractAudioCommand() {
        return new CommandBuilder()
                .withOutputOption(simpleOption("-q:a", "0"))
                .withOutputOption(simpleOption("-map", "a"));
    }

    public CommandBuilder withInputOption(CommandOption commandOption) {
        this.inputOptions.add(commandOption);
        return this;
    }


    public CommandBuilder withInputFile(String inputFile) {
        this.inputFile = inputFile;
        return this;
    }

    public CommandBuilder withOutputOption(CommandOption commandOption) {
        this.outputOptions.add(commandOption);
        return this;
    }

    public CommandBuilder withOutputFile(String outputFile) {
        this.outputFile = outputFile;
        return this;
    }

    public Command build() {
        return new Command(inputOptions, inputFile, outputOptions, outputFile);
    }
}