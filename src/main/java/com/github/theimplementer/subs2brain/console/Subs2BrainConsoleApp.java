package com.github.theimplementer.subs2brain.console;

import com.github.theimplementer.subs2brain.Subs2Brain;
import com.github.theimplementer.subs2brain.options.ApplicationOptions;
import com.github.theimplementer.subs2brain.options.OptionsParser;

public class Subs2BrainConsoleApp {

    public static void main(String[] args) {
        OptionsParser optionsParser = new OptionsParser();
        ApplicationOptions applicationOptions = optionsParser.parse(args);
        Subs2Brain subs2Brain = new Subs2Brain();
        subs2Brain.run(applicationOptions);
    }
}
