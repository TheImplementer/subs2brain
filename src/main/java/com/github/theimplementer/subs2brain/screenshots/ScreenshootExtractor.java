package com.github.theimplementer.subs2brain.screenshots;

import com.github.theimplementer.subs2brain.ffmpeg.Command;
import com.github.theimplementer.subs2brain.ffmpeg.CommandExecutor;
import com.github.theimplementer.subs2brain.ffmpeg.ProcessExecutionException;
import com.github.theimplementer.subs2brain.subs.Subs;
import com.github.theimplementer.subs2brain.subs.SubsEntry;
import com.github.theimplementer.subs2brain.subs.SubsInstant;
import com.github.theimplementer.subs2brain.subs.SubsTimings;

import java.nio.file.Paths;
import java.util.List;

import static com.github.theimplementer.subs2brain.ffmpeg.CommandBuilder.extractScreenshotCommand;
import static com.github.theimplementer.subs2brain.ffmpeg.SimpleOption.simpleOption;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class ScreenshootExtractor {

    private final String inputVideoLocation;
    private final String destinationFolder;
    private final String prefix;
    private final CommandExecutor commandExecutor;

    public ScreenshootExtractor(String inputVideoLocation, String destinationFolder, String prefix, CommandExecutor commandExecutor) {
        this.inputVideoLocation = inputVideoLocation;
        this.destinationFolder = destinationFolder;
        this.prefix = prefix;
        this.commandExecutor = commandExecutor;
    }

    public List<ExtractedScreenshot> extract(Subs subs) throws ExtractScreenshotException {
        List<SubsEntry> entries = subs.getEntries();

        List<Command> commands = entries.stream()
                .map(this::toExtractScreenshotCommand)
                .collect(toList());

        for (Command command : commands) {
            execute(command);
        }

        return entries.stream()
                .map(this::getFileName)
                .map(ExtractedScreenshot::new)
                .collect(toList());
    }

    private void execute(Command command) throws ExtractScreenshotException {
        try {
            commandExecutor.execute(command);
        } catch (ProcessExecutionException e) {
            throw new ExtractScreenshotException(e);
        }
    }

    private Command toExtractScreenshotCommand(SubsEntry entry) {
        SubsTimings timings = entry.getTimings();
        String start = convertInstant(timings.getStartTime());
        String outputFileLocation = outputFileLocation(entry);
        return extractScreenshotCommand()
                .withInputOption(simpleOption("-ss", start))
                .withInputFile(inputVideoLocation)
                .withOutputFile(outputFileLocation)
                .build();
    }

    private String outputFileLocation(SubsEntry entry) {
        String fileName = getFileName(entry);
        return Paths.get(destinationFolder)
                .resolve(fileName)
                .toAbsolutePath()
                .toString();
    }

    private String getFileName(SubsEntry entry) {
        SubsTimings timings = entry.getTimings();
        String start = convertInstant(timings.getStartTime()).replace(":", ".");
        return format("%s_%d_%s.jpg", prefix, entry.getNumber(), start);
    }

    private String convertInstant(SubsInstant instant) {
        return format("%02d:%02d:%02d.%03d", instant.getHour(), instant.getMinute(), instant.getSecond(), instant.getMillis());
    }
}
