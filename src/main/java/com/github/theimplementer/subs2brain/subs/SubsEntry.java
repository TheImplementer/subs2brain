package com.github.theimplementer.subs2brain.subs;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

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

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
