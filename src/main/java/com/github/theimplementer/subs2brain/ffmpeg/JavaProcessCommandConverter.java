package com.github.theimplementer.subs2brain.ffmpeg;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class JavaProcessCommandConverter {

    private final List<JavaCommandOptionConverter> optionConverters;

    public JavaProcessCommandConverter(List<JavaCommandOptionConverter> optionConverters) {
        this.optionConverters = optionConverters;
    }

    public List<String> convert(Command command) {
        List<String> finalCommand = new ArrayList<>();
        finalCommand.add(command.getExecutable());
        finalCommand.addAll(convertOptions(command.getInputOptions()));
        finalCommand.addAll(asList("-i", command.getInputFile()));
        finalCommand.addAll(convertOptions(command.getOutputOptions()));
        finalCommand.add(command.getOutputFile());
        return finalCommand;
    }

    private List<String> convertOptions(List<CommandOption> options) {
        return options.stream()
                .flatMap(option -> {
                    JavaCommandOptionConverter converter = getConverterFor(option);
                    List<String> convertedOption = converter.convert(option);
                    return convertedOption.stream();
                })
                .collect(toList());
    }

    private JavaCommandOptionConverter getConverterFor(CommandOption commandOption) {
        return optionConverters.stream()
                .filter(converter -> converter.canConvert(commandOption))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Cannot convert option: " + commandOption));
    }
}
