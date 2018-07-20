package com.github.theimplementer.subs2brain.output;

import com.github.theimplementer.subs2brain.screenshots.ExtractedScreenshot;
import com.github.theimplementer.subs2brain.subs.Subs;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class OutputConverter {

    public List<OutputEntry> convert(Subs subs, List<ExtractedScreenshot> screenshots) {
        Map<Integer, String> screenshotsByEntryNumber = screenshots.stream()
                .collect(toMap(ExtractedScreenshot::getEntryNumber, ExtractedScreenshot::getFileName));

        return subs.getEntries().stream()
                .map(entry -> new OutputEntry(entry.getLine(), screenshotsByEntryNumber.get(entry.getNumber())))
                .collect(toList());
    }
}
