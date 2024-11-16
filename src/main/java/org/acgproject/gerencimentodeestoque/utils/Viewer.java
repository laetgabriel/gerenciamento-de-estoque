package org.acgproject.gerencimentodeestoque.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.view.App;
import org.acgproject.gerencimentodeestoque.view.controller.CategoriaController;

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

            Stage loginStage = App.getMainStage();
            ScrollPane telaAtual = (ScrollPane) loginStage.getScene().getRoot();
            AnchorPane telaAtualContent = (AnchorPane) telaAtual.getContent();

            Node menuBar = telaAtualContent.getChildren().get(0);

            FXMLLoader loader = new FXMLLoader(Viewer.class.getResource(caminho));
            ScrollPane telaNova = loader.load();
            AnchorPane telaNovaContent = (AnchorPane) telaNova.getContent();

            telaAtualContent.getChildren().clear();
            telaAtualContent.getChildren().add(menuBar);
            telaAtualContent.getChildren().addAll(telaNovaContent.getChildren());

        } catch (IOException e) {
            Alertas.mostrarAlerta("Erro", "Não foi possível carregar a tela.", Alert.AlertType.ERROR);
        }
    }

    public static void loadViewCategoria(String caminho) {
        try {

            Stage loginStage = App.getMainStage();
            ScrollPane telaAtual = (ScrollPane) loginStage.getScene().getRoot();
            AnchorPane telaAtualContent = (AnchorPane) telaAtual.getContent();

            Node menuBar = telaAtualContent.getChildren().get(0);

            FXMLLoader loader = new FXMLLoader(Viewer.class.getResource(caminho));
            ScrollPane telaNova = loader.load();
            AnchorPane telaNovaContent = (AnchorPane) telaNova.getContent();

            telaAtualContent.getChildren().clear();
            telaAtualContent.getChildren().add(menuBar);
            telaAtualContent.getChildren().addAll(telaNovaContent.getChildren());

            CategoriaController controller = loader.getController();
            controller.setCategoriaController(new org.acgproject.gerencimentodeestoque.controller.CategoriaController());
            controller.updateTableView();

        } catch (IOException e) {
            Alertas.mostrarAlerta("Erro", "Não foi possível carregar a tela.", Alert.AlertType.ERROR);
        }
    }
}
