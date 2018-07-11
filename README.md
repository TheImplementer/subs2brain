Project inspired by [subs2srs](http://subs2srs.sourceforge.net/).

Developed in Java so that it can be run on different platforms.

## Requirements

ffmpeg ([https://www.ffmpeg.org/](https://www.ffmpeg.org/)) is required to run the application.

Ubuntu:
```bash
sudo apt-get install ffmpeg
```

Mac OS X:
```bash
brew install ffmpeg
```

## Usage
```
 -a,--extract-audio            Extract audio files
 -i,--video-file <arg>         Video file
 -o,--output-directory <arg>   Output directory
 -p,--prefix <arg>             Prefix for output files
 -s,--subs-file <arg>          Subtitles file
 -ss,--extract-screenshots     Extract screenshots files
```