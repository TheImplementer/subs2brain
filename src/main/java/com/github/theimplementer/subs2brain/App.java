package com.github.theimplementer.subs2brain;

import com.github.theimplementer.subs2brain.options.ApplicationOptions;
import com.github.theimplementer.subs2brain.options.OptionsParser;
import com.github.theimplementer.subs2brain.subs.Subs;
import com.github.theimplementer.subs2brain.subs.SubsParser;

public class App {

    public static void main(String[] args) {
        OptionsParser optionsParser = new OptionsParser();
        ApplicationOptions applicationOptions = optionsParser.parse(args);

        SubsParser subsParser = new SubsParser();
        Subs subs = subsParser.parseSubs(applicationOptions);
    }
}
