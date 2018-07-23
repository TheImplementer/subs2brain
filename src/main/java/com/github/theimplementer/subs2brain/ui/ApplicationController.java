package com.github.theimplementer.subs2brain.ui;

import com.github.theimplementer.subs2brain.Subs2Brain;
import com.github.theimplementer.subs2brain.options.ApplicationOptions;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    public Button convertButton;

    public void onConvertButtonAction(ActionEvent actionEvent) {
        convertButton.setDisable(true);
        ApplicationOptions applicationOptions = new ApplicationOptions(
                subsFileLocation.getText(),
                videoFileLocation.getText(),
                outputDirectory.getText(),
                prefix.getText(),
                extractAudio.isSelected(),
                extractScreenshots.isSelected()
        );
        ApplicationService applicationService = new ApplicationService(applicationOptions);
        applicationService.setOnSucceeded(event -> {
            convertButton.setDisable(false);
        });
        applicationService.start();
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

    private static class ApplicationService extends Service<Void> {

        private final ApplicationOptions applicationOptions;

        private ApplicationService(ApplicationOptions applicationOptions) {
            this.applicationOptions = applicationOptions;
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Subs2Brain subs2Brain = new Subs2Brain();
                    subs2Brain.run(applicationOptions);
                    return null;
                }
            };
        }
    }
}
