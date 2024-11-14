package org.acgproject.gerencimentodeestoque.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/acgproject/gerencimentodeestoque/view" +
                "/Categoria.fxml"));

        ScrollPane scroll = loader.load();

        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);

        Scene scene = new Scene(scroll);
        stage.setTitle("Gerenciamento Estoque");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public static Stage getMainStage() {
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }
}