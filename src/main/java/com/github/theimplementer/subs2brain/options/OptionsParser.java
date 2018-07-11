package com.github.theimplementer.subs2brain.options;

import org.apache.commons.cli.*;

public class OptionsParser {

    private final Options options = new Options()
            .addOption("s", "subs-file", true, "Subtitles file")
            .addOption("i", "video-file", true, "Video file")
            .addOption("o", "output-directory", true, "Output directory")
            .addOption("p", "prefix", true, "Prefix for output files")
            .addOption("a", "extract-audio", false, "Extract audio files")
            .addOption("ss", "extract-screenshots", false, "Extract screenshots files");

    private final CommandLineParser parser = new DefaultParser();

    public ApplicationOptions parse(String[] args) {
        try {
            CommandLine commandLine = parser.parse(options, args);
            String subsFile = commandLine.getOptionValue("s");
            String videoFile = commandLine.getOptionValue("i");
            String outputDirectory = commandLine.getOptionValue("o");
            String outputFilesPrefix = commandLine.getOptionValue("p");
            boolean extractAudio = commandLine.hasOption("a");
            boolean extractScreenshots = commandLine.hasOption("ss");

            return new ApplicationOptions(subsFile, videoFile, outputDirectory, outputFilesPrefix, extractAudio, extractScreenshots);

        } catch (ParseException e) {
            System.out.println("Error while parsing the command line options: " + e);
            throw new RuntimeException(e);
        }
    }
}
