package com.github.theimplementer.subs2brain.output;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.*;
import static java.util.Collections.singletonList;
import static java.util.Comparator.reverseOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertTrue;

public class TsvFileWriterTest {

    private static final String PREFIX = "test-output";

    private Path tempDirectory;
    private TsvFileWriter underTest;

    @Before
    public void setUpOptions() throws Exception {
        tempDirectory = createTempDirectory("");
        underTest = new TsvFileWriter(tempDirectory.toAbsolutePath().toString(), PREFIX);
    }

    @After
    public void cleanUpTempFiles() throws Exception {
        walk(tempDirectory)
                .sorted(reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void writesTheExpectedTsvFileAtTheSpecifiedLocation() throws Exception {
        underTest.write(singletonList(new OutputEntry("Subs line", "test.png")));

        assertTrue(exists(tempDirectory.resolve(PREFIX + ".tsv")));
        assertThat(outputLines(), contains("<img src=\"test.png\">\tSubs line"));
    }

    private List<String> outputLines() {
        try {
            return readAllLines(tempDirectory.resolve(PREFIX + ".tsv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}