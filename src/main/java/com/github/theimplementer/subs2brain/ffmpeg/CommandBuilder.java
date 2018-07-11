package com.github.theimplementer.subs2brain.ffmpeg;

import java.util.ArrayList;
import java.util.List;

import static com.github.theimplementer.subs2brain.ffmpeg.SimpleOption.simpleOption;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class CommandBuilder {

    private static final String FFMPEG_EXECUTABLE = "ffmpeg";

    private String executable;
    private List<CommandOption> inputOptions = new ArrayList<>();
    private String inputFile;
    private List<CommandOption> outputOptions = new ArrayList<>();
    private String outputFile;

    public static CommandBuilder extractAudioCommand() {
        return new CommandBuilder(FFMPEG_EXECUTABLE)
                .withOutputOption(simpleOption("-q:a", "0"))
                .withOutputOption(simpleOption("-map", "a"));
    }

    public static CommandBuilder command(String executable) {
        if (isBlank(executable)) {
            throw new IllegalArgumentException("executable cannot be null or empty");
        }
        return new CommandBuilder(executable);
    }

    private CommandBuilder(String executable) {
        this.executable = executable;
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
        return new Command(executable, inputOptions, inputFile, outputOptions, outputFile);
    }
}