package org.acgproject.gerencimentodeestoque.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.view.App;

import java.io.IOException;

public class Viewer {

    public static void loadView(String caminho) {
        try {
            Stage mainStage = App.getMainStage();
            FXMLLoader loader = new FXMLLoader(Viewer.class.getResource(caminho));
            Parent novaTela = loader.load();
            mainStage.getScene().setRoot(novaTela);
        } catch (IOException e) {
            Alertas.mostrarAlerta("Erro", "Não foi possível carregar a tela.", Alert.AlertType.ERROR);
        }
    }
}
