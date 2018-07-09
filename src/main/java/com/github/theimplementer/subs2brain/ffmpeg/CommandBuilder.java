package com.github.theimplementer.subs2brain.ffmpeg;

import java.util.ArrayList;
import java.util.List;

import static com.github.theimplementer.subs2brain.ffmpeg.SimpleOption.simpleOption;
import static java.lang.String.format;

public class CommandBuilder {

    private List<CommandOption> options = new ArrayList<>();
    private String outputFile;

    public static CommandBuilder extractAudioCommand() {
        return new CommandBuilder()
                .withOption(simpleOption("-q:a", "0"))
                .withOption(simpleOption("-map", "a"));
    }

    public CommandBuilder withInputFile(String inputFile) {
        this.options.add(simpleOption("-i", format("\"%s\"", inputFile)));
        return this;
    }

    public CommandBuilder withOption(CommandOption commandOption) {
        this.options.add(commandOption);
        return this;
    }

    public CommandBuilder withOutputFile(String outputFile) {
        this.outputFile = outputFile;
        return this;
    }

    public Command build() {
        return new Command(options, outputFile);
    }
}