package com.github.theimplementer.subs2brain.ffmpeg;

import org.junit.Test;

import java.util.List;

import static com.github.theimplementer.subs2brain.ffmpeg.IntervalOption.interval;
import static com.github.theimplementer.subs2brain.ffmpeg.SimpleOption.simpleOption;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;

public class JavaIntervalOptionConverterTest {

    private final JavaCommandOptionConverter underTest = new JavaIntervalOptionConverter();

    @Test
    public void supportsConversionOfIntervalOptions() {
        CommandOption commandOption = interval("start", "end");

        assertTrue(underTest.canConvert(commandOption));
    }

    @Test
    public void doesNotSupportConversionOfOtherOptions() {
        CommandOption commandOption = simpleOption("-t", "value");

        assertFalse(underTest.canConvert(commandOption));
    }

    @Test
    public void convertsAnIntervalOptionToTheExpectedFormat() {
        CommandOption commandOption = interval("start", "end");

        List<String> result = underTest.convert(commandOption);

        assertThat(result, contains("-ss", "start", "-t", "end"));
    }

}