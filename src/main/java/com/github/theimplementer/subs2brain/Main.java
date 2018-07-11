package com.github.theimplementer.subs2brain;

import com.github.theimplementer.subs2brain.ffmpeg.JavaIntervalOptionConverter;
import com.github.theimplementer.subs2brain.ffmpeg.JavaProcessCommandConverter;
import com.github.theimplementer.subs2brain.ffmpeg.JavaProcessCommandExecutor;
import com.github.theimplementer.subs2brain.ffmpeg.JavaSimpleOptionConverter;
import com.github.theimplementer.subs2brain.options.ApplicationOptions;
import com.github.theimplementer.subs2brain.options.OptionsParser;
import com.github.theimplementer.subs2brain.output.OutputConverter;
import com.github.theimplementer.subs2brain.output.OutputEntry;
import com.github.theimplementer.subs2brain.output.TsvFileWriter;
import com.github.theimplementer.subs2brain.screenshots.ExtractScreenshotException;
import com.github.theimplementer.subs2brain.screenshots.ExtractedScreenshot;
import com.github.theimplementer.subs2brain.screenshots.ScreenshootExtractor;
import com.github.theimplementer.subs2brain.subs.*;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class Main {

    private final String[] args;

    public Main(String[] args) {
        this.args = args;
    }

    public void run() {
        OptionsParser optionsParser = new OptionsParser();
        ApplicationOptions applicationOptions = optionsParser.parse(args);

        SrtSubsParser srtSubsParser = new SrtSubsParser(new SubsFileReader(), new SrtSubsEntryParser());
        TsvFileWriter writer = new TsvFileWriter(applicationOptions.getOutputDirectory(), applicationOptions.getOutputFilesPrefix());
        OutputConverter outputConverter = new OutputConverter();
        JavaProcessCommandConverter commandConverter = new JavaProcessCommandConverter(asList(
                new JavaSimpleOptionConverter(),
                new JavaIntervalOptionConverter()
        ));
        JavaProcessCommandExecutor commandExecutor = new JavaProcessCommandExecutor(commandConverter);
        ScreenshootExtractor screenshootExtractor = new ScreenshootExtractor(
                applicationOptions.getVideoFileLocation(),
                applicationOptions.getOutputDirectory(),
                applicationOptions.getOutputFilesPrefix(),
                commandExecutor
        );

        Subs subs = parseSubs(applicationOptions, srtSubsParser);

        List<ExtractedScreenshot> screenshots = extractScreenshotsIfNeeded(applicationOptions, screenshootExtractor, subs);

        List<OutputEntry> outputEntries = outputConverter.convert(subs);
        writer.write(outputEntries);

    }

    private List<ExtractedScreenshot> extractScreenshotsIfNeeded(ApplicationOptions applicationOptions, ScreenshootExtractor screenshootExtractor, Subs subs) {

        if (!applicationOptions.extractScreenshots()) {
            return emptyList();
        }

        try {
            return screenshootExtractor.extract(subs);
        } catch (ExtractScreenshotException e) {
            System.err.println("Screenshots could not be extracted.");
            e.printStackTrace(System.err);
            throw new RuntimeException(e);
        }
    }

    private Subs parseSubs(ApplicationOptions applicationOptions, SrtSubsParser parser) {
        try {
            return parser.parseSubs(applicationOptions);
        } catch (SubsFileReadException | SubsParseException e) {
            System.err.println("The specified file cannot be loaded: " + applicationOptions.getSubsFileLocation());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Main(args).run();
    }
}
