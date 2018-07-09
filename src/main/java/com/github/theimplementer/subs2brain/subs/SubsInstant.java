package com.github.theimplementer.subs2brain.subs;

public class SubsInstant {

    private final int hour;
    private final int minute;
    private final int second;
    private final int millis;

    public SubsInstant(int hour, int minute, int second, int millis) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millis = millis;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMillis() {
        return millis;
    }
}
