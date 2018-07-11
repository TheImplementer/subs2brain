package com.github.theimplementer.subs2brain.subs;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

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

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
