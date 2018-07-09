package com.github.theimplementer.subs2brain.subs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.*;

public class SubsEntriesIterator implements Iterator<SubsEntry> {

    private static final Pattern TIMINGS_LINE_REGEX = Pattern.compile("(?<start>\\d{2}:\\d{2}:\\d{2},\\d{3}) --> (?<end>\\d{2}:\\d{2}:\\d{2},\\d{3})");
    private static final Pattern INSTANT_REGEX = Pattern.compile("(?<hour>\\d{2}):(?<minute>\\d{2}):(?<second>\\d{2}),(?<millis>\\d{3})");


    private final List<String> subsFileLines;

    private int currentLine = 0;

    public SubsEntriesIterator(List<String> subsFileLines) {
        this.subsFileLines = subsFileLines;
    }

    @Override
    public boolean hasNext() {
        return currentLine < totalLines();
    }

    @Override
    public SubsEntry next() {
        int lineNumber = Integer.parseInt(nextLine());
        SubsTimings subsTimings = parseTimings(nextLine());

        String nextLine = nextLine();
        List<String> lines = new LinkedList<>();

        while (!isBlank(nextLine)) {
            lines.add(nextLine);
            nextLine = nextLine();
        }
        return new SubsEntry(lineNumber, subsTimings, join(lines, " "));
    }

    private SubsTimings parseTimings(String line) {
        Matcher matcher = TIMINGS_LINE_REGEX.matcher(line);
        if (!matcher.matches()) {
            throw new RuntimeException("Cannot parse subs entry timings: " + line);
        }

        String start = matcher.group("start");
        String end = matcher.group("end");

        return new SubsTimings(parseInstant(start), parseInstant(end));
    }

    private SubsInstant parseInstant(String str) {
        Matcher matcher = INSTANT_REGEX.matcher(str);
        matcher.matches();
        int hour = Integer.parseInt(matcher.group("hour"));
        int minute = Integer.parseInt(matcher.group("minute"));
        int second = Integer.parseInt(matcher.group("second"));
        int millis = Integer.parseInt(matcher.group("millis"));
        return new SubsInstant(hour, minute, second, millis);
    }

    private String nextLine() {
        String line = subsFileLines.get(currentLine);
        currentLine++;
        return trim(line).replace("\uFEFF", "");
    }

    private int totalLines() {
        return subsFileLines.size();
    }
}
