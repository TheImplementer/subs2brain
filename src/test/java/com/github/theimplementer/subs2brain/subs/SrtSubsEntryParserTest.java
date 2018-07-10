package com.github.theimplementer.subs2brain.subs;

import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SrtSubsEntryParserTest {

    private final SrtSubsEntryParser underTest = new SrtSubsEntryParser();

    @Test(expected = SubsEntryParseException.class)
    public void throwsAnExceptionForEmptyEntry() throws Exception {
        underTest.parse(emptyList());
    }

    @Test(expected = SubsEntryParseException.class)
    public void throwsAnExceptionIfLessThanMinimumLinesForAnEntry() throws Exception {
        underTest.parse(singletonList("1"));
    }

    @Test(expected = SubsEntryParseException.class)
    public void throwsAnExceptionIfTheFirstLineIsNotANumber() throws Exception {
        underTest.parse(asList(
                "a",
                "00:00:04,400 --> 00:00:07,410",
                "Sub line"
        ));
    }

    @Test(expected = SubsEntryParseException.class)
    public void throwsAnExceptionIfTheSecondLineIsNotAValidInterval() throws Exception {
        underTest.parse(asList(
                "1",
                "04,400 -> 00:00:07,410",
                "Sub line"
        ));
    }

    @Test
    public void parsesAValidEntryAsExpected() throws Exception {
        SubsEntry entry = underTest.parse(asList(
                "1",
                "00:00:04,400 --> 00:00:07,410",
                "Sub line"
        ));

        assertThat(entry.getNumber(), is(1));
        assertThat(entry.getTimings(), is(new SubsTimings(
                instant(0, 0, 4, 400),
                instant(0, 0, 7, 410)
        )));
        assertThat(entry.getLine(), is("Sub line"));
    }


    @Test
    public void mergesMultipleLinesIntoOne() throws Exception {
        SubsEntry entry = underTest.parse(asList(
                "1",
                "00:00:04,400 --> 00:00:07,410",
                "Sub line",
                "Second Line"
        ));

        assertThat(entry.getNumber(), is(1));
        assertThat(entry.getTimings(), is(new SubsTimings(
                instant(0, 0, 4, 400),
                instant(0, 0, 7, 410)
        )));
        assertThat(entry.getLine(), is("Sub line Second Line"));
    }

    private SubsInstant instant(int hour, int minute, int second, int millis) {
        return new SubsInstant(hour, minute, second, millis);
    }
}