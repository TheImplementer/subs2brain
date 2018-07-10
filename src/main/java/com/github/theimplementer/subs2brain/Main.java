package com.github.theimplementer.subs2brain;

import com.github.theimplementer.subs2brain.options.ApplicationOptions;
import com.github.theimplementer.subs2brain.options.OptionsParser;
import com.github.theimplementer.subs2brain.subs.*;

public class Main {

    private final String[] args;

    public Main(String[] args) {
        this.args = args;
    }

    public void run() {
        OptionsParser optionsParser = new OptionsParser();
        ApplicationOptions applicationOptions = optionsParser.parse(args);

        SrtSubsParser srtSubsParser = new SrtSubsParser(new SubsFileReader(), new SrtSubsEntryParser());
        try {
            Subs subs = srtSubsParser.parseSubs(applicationOptions);
        } catch (SubsFileReadException | SubsParseException e) {
            System.err.println("The specified file cannot be loaded: " + applicationOptions.getSubsFileLocation());
        }
    }

    public static void main(String[] args) {
        new Main(args).run();
    }
}
