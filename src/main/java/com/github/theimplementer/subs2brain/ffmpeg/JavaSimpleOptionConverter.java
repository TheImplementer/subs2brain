package com.github.theimplementer.subs2brain.ffmpeg;

import java.util.List;

import static java.util.Arrays.asList;

public class JavaSimpleOptionConverter implements JavaCommandOptionConverter {

    @Override
    public boolean canConvert(CommandOption commandOption) {
        return commandOption instanceof SimpleOption;
    }

    @Override
    public List<String> convert(CommandOption commandOption) {
        SimpleOption simpleOption = (SimpleOption) commandOption;
        return asList(simpleOption.getOption(), simpleOption.getValue());
    }
}
