package com.github.theimplementer.subs2brain.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Subs2BrainApp extends Application {

    private static final String APPLICATION_LAYOUT_FILE = "/applicationLayout.fxml";
    private static final String TITLE = "Subs2Brain";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = loadApplicationLayout();

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private Parent loadApplicationLayout() throws java.io.IOException {
        Parent layout = FXMLLoader.load(getClass().getResource(APPLICATION_LAYOUT_FILE));
        if (layout == null) {
            throw new IllegalStateException("Could not load layout file: " + APPLICATION_LAYOUT_FILE);
        }
        return layout;
    }
}
