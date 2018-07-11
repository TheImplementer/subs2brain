package com.github.theimplementer.subs2brain.ffmpeg;

import java.io.IOException;
import java.util.List;

public class JavaProcessCommandExecutor implements CommandExecutor {

    private final JavaProcessCommandConverter commandConverter;

    public JavaProcessCommandExecutor(JavaProcessCommandConverter commandConverter) {
        this.commandConverter = commandConverter;
    }

    @Override
    public void execute(Command command) throws ProcessExecutionException {
        List<String> convertedCommand = commandConverter.convert(command);
        ProcessBuilder processBuilder = new ProcessBuilder(convertedCommand);
        run(processBuilder);
    }

    private void run(ProcessBuilder processBuilder) throws ProcessExecutionException {
        try {
            processBuilder.start();
        } catch (IOException e) {
            throw new ProcessExecutionException(e);
        }
    }

}
