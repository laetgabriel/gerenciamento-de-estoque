package org.acgproject.gerencimentodeestoque.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.view.App;

import java.io.IOException;

public class Viewer {

    public static void loadView(String caminho) {
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
}
