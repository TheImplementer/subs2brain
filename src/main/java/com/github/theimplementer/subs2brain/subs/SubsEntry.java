package com.github.theimplementer.subs2brain.subs;

public class SubsEntry {

    private final int number;
    private final SubsTimings subsTimings;
    private final String line;

    public SubsEntry(int number, SubsTimings subsTimings, String line) {
        this.number = number;
        this.subsTimings = subsTimings;
        this.line = line;
    }

    public int getNumber() {
        return number;
    }

    public SubsTimings getSubsTimings() {
        return subsTimings;
    }

    public String getLine() {
        return line;
    }
}
