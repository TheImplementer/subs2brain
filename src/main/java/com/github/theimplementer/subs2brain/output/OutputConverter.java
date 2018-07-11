package com.github.theimplementer.subs2brain.output;

import com.github.theimplementer.subs2brain.subs.Subs;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OutputConverter {

    public List<OutputEntry> convert(Subs subs) {
        return subs.getEntries().stream()
                .map(entry -> new OutputEntry(entry.getLine()))
                .collect(toList());
    }
}
