package com.github.theimplementer.subs2brain.output;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class OutputEntry {

    private final String line;
    private final String screenshotFilename;

    public OutputEntry(String line, String screenshotFilename) {
        this.line = line;
        this.screenshotFilename = screenshotFilename;
    }

    public String getLine() {
        return line;
    }

    public String getScreenshotFilename() {
        return screenshotFilename;
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
