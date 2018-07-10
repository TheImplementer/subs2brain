package com.github.theimplementer.subs2brain.ffmpeg;

import java.util.List;

import static java.util.Arrays.asList;

public class JavaIntervalOptionConverter implements JavaCommandOptionConverter {

    @Override
    public boolean canConvert(CommandOption commandOption) {
        return commandOption instanceof IntervalOption;
    }

    @Override
    public List<String> convert(CommandOption commandOption) {
        IntervalOption intervalOption = (IntervalOption) commandOption;
        return asList("-ss", intervalOption.getPosition(), "-t", intervalOption.getDuration());
    }
}
