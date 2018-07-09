package com.github.theimplementer.subs2brain.subs;

import java.util.List;

public class Subs {

    private final List<SubsEntry> entries;

    public Subs(List<SubsEntry> entries) {
        this.entries = entries;
    }

    public List<SubsEntry> getEntries() {
        return entries;
    }
}
