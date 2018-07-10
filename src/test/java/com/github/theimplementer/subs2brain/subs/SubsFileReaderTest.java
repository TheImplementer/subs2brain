package com.github.theimplementer.subs2brain.subs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.delete;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class SubsFileReaderTest {

    private static final String FIRST_LINE = "first";
    private static final String SECOND_LINE = "second";
    private static final String THIRD_LINE = "third";

    private String tempFileLocation;

    private final SubsFileReader underTest = new SubsFileReader();

    @Before
    public void createTestFile() throws IOException {
        File tempFile = createTempFile("", "").toFile();
        tempFileLocation = tempFile.getAbsolutePath();
        PrintWriter printWriter = new PrintWriter(tempFile);
        printWriter.println(FIRST_LINE);
        printWriter.println(SECOND_LINE);
        printWriter.println(THIRD_LINE);
        printWriter.close();
    }

    @After
    public void cleanUpFile() throws IOException {
        delete(Paths.get(tempFileLocation));
    }

    @Test
    public void shouldReturnTheSpecifiedFileContent() throws Exception {
        List<String> lines = underTest.read(tempFileLocation);

        assertThat(lines, contains(FIRST_LINE, SECOND_LINE, THIRD_LINE));
    }

    @Test(expected = SubsFileReadException.class)
    public void shouldThrowAnExceptionIfTheSpecifiedFileDoesNotExist() throws Exception {
        underTest.read("unexistent-file.test");
    }

}