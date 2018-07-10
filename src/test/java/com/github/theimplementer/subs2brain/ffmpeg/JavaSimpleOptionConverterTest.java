package com.github.theimplementer.subs2brain.ffmpeg;

import org.junit.Test;

import java.util.List;

import static com.github.theimplementer.subs2brain.ffmpeg.IntervalOption.interval;
import static com.github.theimplementer.subs2brain.ffmpeg.SimpleOption.simpleOption;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.*;

public class JavaSimpleOptionConverterTest {

    private final JavaCommandOptionConverter underTest = new JavaSimpleOptionConverter();

    @Test
    public void supportsConversionOfSimpleOptions() {
        CommandOption commandOption = simpleOption("-t", "value");

        assertTrue(underTest.canConvert(commandOption));
    }

    @Test
    public void doesNotSupportConversionOfOtherOptions() {
        CommandOption commandOption = interval("start", "end");

        assertFalse(underTest.canConvert(commandOption));
    }

    @Test
    public void convertsASimpleOptionToTheExpectedFormat() {
        CommandOption commandOption = simpleOption("-t", "value");

        List<String> result = underTest.convert(commandOption);

        assertThat(result, contains("-t", "value"));
    }

}