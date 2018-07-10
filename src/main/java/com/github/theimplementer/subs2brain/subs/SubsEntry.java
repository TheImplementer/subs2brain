package com.github.theimplementer.subs2brain.subs;

public class SubsEntry {

    private final int number;
    private final SubsTimings timings;
    private final String line;

    public SubsEntry(int number, SubsTimings timings, String line) {
        this.number = number;
        this.timings = timings;
        this.line = line;
    }

    public int getNumber() {
        return number;
    }

    public SubsTimings getTimings() {
        return timings;
    }

    public String getLine() {
        return line;
    }
}
