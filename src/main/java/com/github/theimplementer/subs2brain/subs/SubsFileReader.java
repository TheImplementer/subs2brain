package com.github.theimplementer.subs2brain.subs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class SubsFileReader {

    public List<String> read(String fileLocation) throws SubsFileReadException {
        try {
            return readAllLines(Paths.get(fileLocation), Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new SubsFileReadException(e);
        }
    }
}
