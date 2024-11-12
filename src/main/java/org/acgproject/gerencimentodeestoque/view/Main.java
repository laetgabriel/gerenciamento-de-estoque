package org.acgproject.gerencimentodeestoque.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = FXMLLoader.load(getClass().getResource("/org/acgproject/gerencimentodeestoque/view/Categoria.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gerenciamento Estoque");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}