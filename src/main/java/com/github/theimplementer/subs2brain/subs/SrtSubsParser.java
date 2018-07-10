package com.github.theimplementer.subs2brain.subs;

import com.github.theimplementer.subs2brain.options.ApplicationOptions;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class SrtSubsParser {

    private final SubsFileReader subsFileReader;
    private final SrtSubsEntryParser srtSubsEntryParser;

    public SrtSubsParser(SubsFileReader subsFileReader, SrtSubsEntryParser srtSubsEntryParser) {
        this.subsFileReader = subsFileReader;
        this.srtSubsEntryParser = srtSubsEntryParser;
    }

    public Subs parseSubs(ApplicationOptions applicationOptions) throws SubsFileReadException, SubsParseException {
        List<String> subsLines = subsFileReader.read(applicationOptions.getSubsFileLocation());

        List<SubsEntry> parsedEntries = new ArrayList<>();
        List<String> currentEntry = new ArrayList<>();

        for (String currentLine : subsLines) {
            if (isBlank(currentLine)) {
                parsedEntries.add(parse(currentEntry));
                currentEntry.clear();
            } else {
                currentEntry.add(currentLine);
            }
        }

        if (currentEntry.size() > 0) {
            parsedEntries.add(parse(currentEntry));
        }

        return new Subs(parsedEntries);
    }

    private SubsEntry parse(List<String> entryLines) throws SubsParseException {
        try {
            return srtSubsEntryParser.parse(entryLines);
        } catch (SubsEntryParseException e) {
            throw new SubsParseException();
        }
    }
}
