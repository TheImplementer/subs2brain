package com.github.theimplementer.subs2brain.subs;

import com.github.theimplementer.subs2brain.options.ApplicationOptions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SrtSubsParserTest {

    private static final String FILE_LOCATION = "file-location.test";

    private final ApplicationOptions options = mock(ApplicationOptions.class);
    private final SubsFileReader subsFileReader = mock(SubsFileReader.class);
    private final SrtSubsEntryParser srtSubsEntryParser = mock(SrtSubsEntryParser.class);
    private final SrtSubsParser underTest = new SrtSubsParser(subsFileReader, srtSubsEntryParser);

    @Before
    public void setUpOptions() {
        when(options.getSubsFileLocation()).thenReturn(FILE_LOCATION);
    }

    @Test(expected = SubsFileReadException.class)
    public void throwsAnExceptionIfTheSubsFileCannotBeLoaded() throws Exception {
        SubsFileReadException readException = mock(SubsFileReadException.class);
        when(subsFileReader.read(FILE_LOCATION)).thenThrow(readException);

        underTest.parseSubs(options);
    }

    @Test
    public void parsesAFileWithNoEntriesAsExpected() throws Exception {
        when(subsFileReader.read(FILE_LOCATION)).thenReturn(emptyList());

        Subs result = underTest.parseSubs(options);

        assertThat(result.getEntries(), hasSize(0));
    }

    @Test
    public void parsesAFileWithOneEntryAsExpected() throws Exception {
        when(subsFileReader.read(FILE_LOCATION)).thenReturn(aSubsFileWithOneEntry());
        SubsEntry entry = mock(SubsEntry.class);
        when(srtSubsEntryParser.parse(aSubsEntry())).thenReturn(entry);

        Subs result = underTest.parseSubs(options);

        assertThat(result.getEntries(), contains(entry));
    }

    @Test
    public void parsesAFileWithMultipleEntriesAsExpected() throws Exception {
        when(subsFileReader.read(FILE_LOCATION)).thenReturn(aSubsFileWithMultipleEntries());
        SubsEntry entry = mock(SubsEntry.class);
        when(srtSubsEntryParser.parse(aSubsEntry())).thenReturn(entry);
        SubsEntry secondEntry = mock(SubsEntry.class);
        when(srtSubsEntryParser.parse(anotherSubsEntry())).thenReturn(secondEntry);

        Subs result = underTest.parseSubs(options);

        assertThat(result.getEntries(), contains(entry, secondEntry));
    }

    @Test(expected = SubsParseException.class)
    public void throwsAnExceptionIfAnEntryCannotBeParsed() throws Exception {
        when(subsFileReader.read(FILE_LOCATION)).thenReturn(aSubsFileWithOneEntry());
        when(srtSubsEntryParser.parse(anyList())).thenThrow(new SubsEntryParseException());

        underTest.parseSubs(options);
    }

    private List<String> aSubsFileWithMultipleEntries() {
        List<String> subs = new ArrayList<>();
        subs.addAll(aSubsEntry());
        subs.add("");
        subs.addAll(anotherSubsEntry());
        subs.add("");
        return subs;

    }

    public List<String> aSubsEntry() {
        return asList(
                "1",
                "00:00:04,400 --> 00:00:07,410",
                "Test sub"
        );
    }

    public List<String> anotherSubsEntry() {
        return asList(
                "2",
                "00:00:08,400 --> 00:00:12,410",
                "Another Line"
        );
    }

    public List<String> aSubsFileWithOneEntry() {
        List<String> subs = new ArrayList<>();
        subs.addAll(aSubsEntry());
        subs.add("");
        return subs;
    }

}