package com.github.theimplementer.subs2brain.ffmpeg;

import org.junit.Test;

import static com.github.theimplementer.subs2brain.ffmpeg.CommandBuilder.command;

public class CommandBuilderTest {

    @Test(expected = IllegalArgumentException.class)
    public void commandsCannotBeCreatedWithANullExecutable() throws Exception {
        command(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void commandsCannotBeCreatedWithAnEmptyStringAsExecutable() throws Exception {
        command("");
    }
}