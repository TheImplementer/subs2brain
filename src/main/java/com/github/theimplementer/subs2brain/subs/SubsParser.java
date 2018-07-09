package com.github.theimplementer.subs2brain.subs;

import com.github.theimplementer.subs2brain.options.ApplicationOptions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class SubsParser {

    public Subs parseSubs(ApplicationOptions applicationOptions) {
        File subsFile = new File(applicationOptions.getSubsFile());
        List<String> fileLines = getFileLines(subsFile);
        SubsEntriesIterator subsEntriesIterator = new SubsEntriesIterator(fileLines);
        List<SubsEntry> subsEntries = new LinkedList<>();

        while (subsEntriesIterator.hasNext()) {
            subsEntries.add(subsEntriesIterator.next());
        }

        return new Subs(subsEntries);
    }

    private List<String> getFileLines(File subsFile) {
        try {
            return readAllLines(subsFile.toPath(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
