package com.github.theimplementer.subs2brain.output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.toList;

public class TsvFileWriter {

    private static final String OUTPUT_FILE_EXTENSION = "tsv";
    private static final String SCREENSHOT_ENTRY_TEMPLATE = "<img src=\"%s\">";

    private final String outputDirectory;
    private final String prefix;

    public TsvFileWriter(String outputDirectory, String prefix) {
        this.outputDirectory = outputDirectory;
        this.prefix = prefix;
    }

    public void write(List<OutputEntry> entries) {
        List<String> outputLines = entries.stream()
                .map(this::toFileLine)
                .collect(toList());

        Path outputFilePath = getOutputFilePath();
        try {
            Files.write(outputFilePath, outputLines, UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toFileLine(OutputEntry outputEntry) {
        String screenshotEntry = format(SCREENSHOT_ENTRY_TEMPLATE, outputEntry.getScreenshotFilename());
        return format("%s\t%s", screenshotEntry, outputEntry.getLine());
    }

    private Path getOutputFilePath() {
        return Paths.get(outputDirectory)
                .resolve(format("%s.%s", prefix, OUTPUT_FILE_EXTENSION));
    }
}
