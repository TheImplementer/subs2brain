package com.github.theimplementer.subs2brain.subs;

public class SubsTimings {

    private final SubsInstant startTime;
    private final SubsInstant endTime;

    public SubsTimings(SubsInstant startTime, SubsInstant endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SubsInstant getStartTime() {
        return startTime;
    }

    public SubsInstant getEndTime() {
        return endTime;
    }
}
