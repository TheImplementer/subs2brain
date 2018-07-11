package com.github.theimplementer.subs2brain.output;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class OutputEntry {

    private final String line;

    public OutputEntry(String line) {
        this.line = line;
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
