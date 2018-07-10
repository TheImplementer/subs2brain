package com.github.theimplementer.subs2brain.subs;

import org.apache.commons.io.input.BOMInputStream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.apache.commons.io.IOUtils.readLines;

public class SubsFileReader {

    public List<String> read(String fileLocation) throws SubsFileReadException {
        try {
            BOMInputStream bomInputStream = new BOMInputStream(new FileInputStream(fileLocation), false);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(bomInputStream);
            return readLines(bufferedInputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SubsFileReadException(e);
        }
    }
}
