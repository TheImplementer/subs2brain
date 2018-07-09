package com.github.theimplementer.subs2brain.ffmpeg;

public class SimpleOption implements CommandOption {

    private final String option;
    private final String value;

    public static CommandOption simpleOption(String option, String value) {
        return new SimpleOption(option, value);
    }

    private SimpleOption(String option, String value) {
        this.option = option;
        this.value = value;
    }


    public String getOption() {
        return option;
    }

    public String getValue() {
        return value;
    }
}
