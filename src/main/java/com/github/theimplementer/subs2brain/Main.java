package com.github.theimplementer.subs2brain;

import com.github.theimplementer.subs2brain.options.ApplicationOptions;
import com.github.theimplementer.subs2brain.options.OptionsParser;
import com.github.theimplementer.subs2brain.subs.Subs;
import com.github.theimplementer.subs2brain.subs.SubsParser;

public class Main {

    private final String[] args;

    public Main(String[] args) {
        this.args = args;
    }

    public void run() {
        OptionsParser optionsParser = new OptionsParser();
        ApplicationOptions applicationOptions = optionsParser.parse(args);


        SubsParser subsParser = new SubsParser();
        Subs subs = subsParser.parseSubs(applicationOptions);

    }

    public static void main(String[] args) {
        new Main(args).run();
    }
}
