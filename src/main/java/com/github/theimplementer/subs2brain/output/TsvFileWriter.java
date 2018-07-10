package com.github.theimplementer.subs2brain.output;

import com.github.theimplementer.subs2brain.options.ApplicationOptions;

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

    private final ApplicationOptions options;

    public TsvFileWriter(ApplicationOptions options) {
        this.options = options;
    }

    public void write(List<OutputEntry> entries) {
        List<String> outputLines = entries.stream()
                .map(OutputEntry::getLine)
                .collect(toList());

        Path outputFilePath = getOutputFilePath();
        try {
            Files.write(outputFilePath, outputLines, UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getOutputFilePath() {
        return Paths.get(options.getOutputDirectory())
                .resolve(format("%s.%s", options.getOutputFilesPrefix(), OUTPUT_FILE_EXTENSION));
    }
}
