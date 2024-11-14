package org.acgproject.gerencimentodeestoque.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.utils.Viewer;

import java.io.IOException;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view" +
        "/Categoria.fxml");
    }

    public static Stage getMainStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}