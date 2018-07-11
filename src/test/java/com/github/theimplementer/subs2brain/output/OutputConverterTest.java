package com.github.theimplementer.subs2brain.output;

import com.github.theimplementer.subs2brain.subs.Subs;
import com.github.theimplementer.subs2brain.subs.SubsEntry;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class OutputConverterTest {

    private static final String LINE = "Test line";

    private final OutputConverter underTest = new OutputConverter();

    @Test
    public void generatesNoOutputEntriesForNoSubs() throws Exception {
        List<OutputEntry> output = underTest.convert(new Subs(emptyList()));

        assertThat(output, hasSize(0));
    }

    @Test
    public void convertsSubsAsExpected() throws Exception {
        List<OutputEntry> output = underTest.convert(new Subs(singletonList(aSubsEntry())));

        assertThat(output, contains(new OutputEntry(LINE)));
    }

    private SubsEntry aSubsEntry() {
        return new SubsEntry(1, null, LINE);
    }
}