package org.acgproject.gerencimentodeestoque.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.view.App;

import java.io.IOException;

public class Viewer {

    public static void loadViewCadastro(String caminho) {
        try {
            Stage mainStage = App.getMainStage();
            Stage newStage = new Stage();

            FXMLLoader loader = new FXMLLoader(Viewer.class.getResource(caminho));
            Parent novaTela = loader.load();
            Scene cenaTela = new Scene(novaTela);
            newStage.setResizable(false);
            newStage.setTitle("Gerenciamento Estoque");
            newStage.setScene(cenaTela);
            newStage.initOwner(mainStage);
            newStage.initModality(Modality.WINDOW_MODAL);
            newStage.showAndWait();

        } catch (IOException e) {
            Alertas.mostrarAlerta("Erro", "Não foi possível carregar a tela.", Alert.AlertType.ERROR);
        }
    }

    public static void loadView(String caminho) {
        try {
            FXMLLoader loader = new FXMLLoader(Viewer.class.getResource(caminho));
            ScrollPane scroll = loader.load();

            Stage mainStage = App.getMainStage();

            scroll.setFitToWidth(true);
            scroll.setFitToHeight(true);

            Scene scene = new Scene(scroll);
            mainStage.setTitle("Gerenciamento Estoque");
            mainStage.setScene(scene);
            mainStage.centerOnScreen();
            mainStage.show();

        } catch (IOException e) {
            Alertas.mostrarAlerta("Erro", "Não foi possível carregar a tela.", Alert.AlertType.ERROR);
        }
    }
}
