package com.github.theimplementer.subs2brain.ffmpeg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaProcessCommandExecutor {

    private static final String FFMPEG_EXECUTABLE_NAME = "ffmpeg";

    private final JavaProcessCommandConverter commandConverter;

    public JavaProcessCommandExecutor(JavaProcessCommandConverter commandConverter) {
        this.commandConverter = commandConverter;
    }

    public void execute(Command command) {
        List<String> convertedCommand = commandConverter.convert(command);
        ProcessBuilder processBuilder = getProcessBuildFor(convertedCommand);
        try {
            processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ProcessBuilder getProcessBuildFor(List<String> command) {
        List<String> commandList = new ArrayList<>();
        commandList.add(FFMPEG_EXECUTABLE_NAME);
        commandList.addAll(command);
        return new ProcessBuilder(commandList).inheritIO();
    }
}
