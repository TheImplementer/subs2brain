package com.github.theimplementer.subs2brain.ffmpeg;

public class IntervalOption implements CommandOption {

    private final String position;
    private final String duration;

    public static CommandOption interval(String position, String duration) {
        return new IntervalOption(position, duration);
    }

    IntervalOption(String position, String duration) {
        this.position = position;
        this.duration = duration;
    }

    @Override
    public String get() {
        return String.format("-ss %s -t %s", position, duration);
    }
}
