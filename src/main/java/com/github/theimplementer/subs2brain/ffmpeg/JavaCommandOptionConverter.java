package com.github.theimplementer.subs2brain.ffmpeg;

import java.util.List;

public interface JavaCommandOptionConverter {

    boolean canConvert(CommandOption commandOption);

    List<String> convert(CommandOption commandOption);
}
