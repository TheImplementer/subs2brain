package com.github.theimplementer.subs2brain.screenshots;

public class ExtractedScreenshot {

    private final String fileName;
    private final int entryNumber;

    public ExtractedScreenshot(String fileName, int entryNumber) {
        this.fileName = fileName;
        this.entryNumber = entryNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public int getEntryNumber() {
        return entryNumber;
    }
}
