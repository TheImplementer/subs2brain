package com.github.theimplementer.subs2brain.ffmpeg;

import static java.lang.String.format;

public class SimpleOption implements CommandOption {

    private final String option;
    private final String value;

    public static CommandOption simpleOption(String option, String value) {
        return new SimpleOption(option, value);
    }

    SimpleOption(String option, String value) {
        this.option = option;
        this.value = value;
    }

    @Override
    public String get() {
        return format("%s %s", option, value);
    }
}
