package com.github.theimplementer.subs2brain.ffmpeg;

public interface CommandExecutor {
    void execute(Command command) throws ProcessExecutionException;
}
