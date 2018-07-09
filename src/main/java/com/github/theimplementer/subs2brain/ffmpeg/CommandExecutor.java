package com.github.theimplementer.subs2brain.ffmpeg;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.Executor;

import java.io.IOException;

public class CommandExecutor {

    public static final String FFMPEG_EXECUTABLE_NAME = "ffmpeg";
    private final Executor executor;

    public CommandExecutor(Executor executor) {
        this.executor = executor;
    }

    public void execute(Command command) {
        CommandLine commandLine = getCommandLineFor(command);
        try {
            executor.execute(commandLine);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CommandLine getCommandLineFor(Command command) {
        CommandLine commandLine = new CommandLine(FFMPEG_EXECUTABLE_NAME);
        command.getOptions().stream()
                .map(CommandOption::get)
                .forEach(commandLine::addArgument);
        return commandLine;
    }
}
