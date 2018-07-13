package com.github.theimplementer.subs2brain.ui;

import com.github.theimplementer.subs2brain.Subs2Brain;
import com.github.theimplementer.subs2brain.options.ApplicationOptions;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

public class ApplicationController {

    public Node container;
    public TextField subsFileLocation;
    public TextField videoFileLocation;
    public TextField outputDirectory;
    public TextField prefix;
    public CheckBox extractScreenshots;
    public CheckBox extractAudio;

    public void onConvertButtonAction(ActionEvent actionEvent) {
        ApplicationOptions applicationOptions = new ApplicationOptions(
                subsFileLocation.getText(),
                videoFileLocation.getText(),
                outputDirectory.getText(),
                prefix.getText(),
                extractAudio.isSelected(),
                extractScreenshots.isSelected()
        );
        Subs2Brain subs2Brain = new Subs2Brain();
        subs2Brain.run(applicationOptions);
    }

    public void onSubsFileLocationAction(ActionEvent actionEvent) {
        showFileChooser("SRT files (*.srt)", "*.srt", subsFileLocation);
    }

    public void onVideoFileLocationAction(ActionEvent actionEvent) {
        showFileChooser("MP4 video files (*.mp4)", "*.mp4", videoFileLocation);
    }

    private void showFileChooser(String extensionDescription, String extension, TextField updateField) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(extensionDescription, extension);
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(container.getScene().getWindow());
        if (file != null) {
            updateField.setText(file.getAbsolutePath());
        }
    }

    public void onOutputDirectoryAction(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(container.getScene().getWindow());
        if (directory != null) {
            outputDirectory.setText(directory.getAbsolutePath());
        }
    }
}
