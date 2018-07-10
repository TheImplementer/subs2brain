package com.github.theimplementer.subs2brain.subs;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class SrtSubsEntryParser {

    private static final Pattern TIMINGS_LINE_REGEX = Pattern.compile("(?<start>\\d{2}:\\d{2}:\\d{2},\\d{3}) --> (?<end>\\d{2}:\\d{2}:\\d{2},\\d{3})");
    private static final Pattern INSTANT_REGEX = Pattern.compile("(?<hour>\\d{2}):(?<minute>\\d{2}):(?<second>\\d{2}),(?<millis>\\d{3})");
    private static final int MINIMUM_ENTRY_LINES = 3;

    public SubsEntry parse(List<String> currentEntry) throws SubsEntryParseException {
        if (currentEntry.size() < MINIMUM_ENTRY_LINES) {
            throw new SubsEntryParseException();
        }

        int number = parseEntryNumber(currentEntry.get(0));
        SubsTimings timings = parseEntryTimings(currentEntry.get(1));

        List<String> remainingLines = currentEntry.subList(2, currentEntry.size());

        StringBuilder subsLine = new StringBuilder(remainingLines.get(0));
        remainingLines.stream().skip(1)
                .forEach(line -> {
                    subsLine.append(" ");
                    subsLine.append(line);
                });

        return new SubsEntry(number, timings, subsLine.toString());
    }

    private SubsTimings parseEntryTimings(String line) throws SubsEntryParseException {
        Matcher matcher = TIMINGS_LINE_REGEX.matcher(line);
        if (!matcher.matches()) {
            throw new SubsEntryParseException();
        }

        String start = matcher.group("start");
        String end = matcher.group("end");

        return new SubsTimings(parseInstant(start), parseInstant(end));
    }

    private SubsInstant parseInstant(String str) {
        Matcher matcher = INSTANT_REGEX.matcher(str);
        matcher.matches();
        int hour = parseInt(matcher.group("hour"));
        int minute = parseInt(matcher.group("minute"));
        int second = parseInt(matcher.group("second"));
        int millis = parseInt(matcher.group("millis"));
        return new SubsInstant(hour, minute, second, millis);
    }

    private int parseEntryNumber(String line) throws SubsEntryParseException {
        try {
            return parseInt(line);
        } catch (NumberFormatException e) {
            throw new SubsEntryParseException();
        }
    }
}
