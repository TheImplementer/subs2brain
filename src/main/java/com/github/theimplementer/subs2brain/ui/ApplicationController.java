package com.github.theimplementer.subs2brain.ui;

import com.github.theimplementer.subs2brain.Subs2Brain;
import com.github.theimplementer.subs2brain.options.ApplicationOptions;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ApplicationController {

    @FXML
    public Node container;
    @FXML
    public TextField subsFileLocation;
    @FXML
    public TextField videoFileLocation;
    @FXML
    public TextField outputDirectory;
    @FXML
    public TextField prefix;
    @FXML
    public CheckBox extractScreenshots;
    @FXML
    public CheckBox extractAudio;
    @FXML
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
        FXMLLoader loader = createProgressWindow();
        ProgressController progressController = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Conversion in progress...");
        stage.setResizable(false);
        stage.setOnCloseRequest(Event::consume);
        stage.setScene(new Scene(loader.getRoot()));

        ApplicationService applicationService = new ApplicationService(applicationOptions);
        applicationService.setOnSucceeded(event -> {
            convertButton.setDisable(false);
            stage.close();
        });
        applicationService.start();

        stage.showAndWait();
    }

    private FXMLLoader createProgressWindow() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/progressWindow.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException("Cannot load progress window from FXML file.");
        }
        return loader;
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
