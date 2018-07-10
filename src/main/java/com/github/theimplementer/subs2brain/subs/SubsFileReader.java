package com.github.theimplementer.subs2brain.subs;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllLines;

public class SubsFileReader {

    public List<String> read(String fileLocation) throws SubsFileReadException {
        try {
            return readAllLines(Paths.get(fileLocation), UTF_8);
        } catch (IOException e) {
            throw new SubsFileReadException(e);
        }
    }
}
