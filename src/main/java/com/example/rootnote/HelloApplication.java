package com.example.rootnote;

import NotePage.NotePage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    // region Attributes
    private Stage window = null;
    public Stage getWindow() {
        return window;
    }

    private Scene currentScene = null;
    public Scene getCurrentScene() {
        return currentScene;
    }
    public void setCurrentScene(Scene scene) {
        this.currentScene = scene;
        window.setScene(currentScene);
    }
    // endregion

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        stage.setTitle("Hello!");
        stage.show();
        NotePage page = new NotePage();
        setCurrentScene(page.getScene());
    }

    public static void main(String[] args) {
        launch();
    }
}