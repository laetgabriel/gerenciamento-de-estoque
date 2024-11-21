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
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.view.App;
import org.acgproject.gerencimentodeestoque.view.controller.*;
import org.acgproject.gerencimentodeestoque.view.observer.CategoriaObserver;

import java.io.IOException;
import java.util.function.Consumer;

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

    public static <T> void loadView(String caminho, Consumer<T> configuracaoController) {
        try {
            Stage mainStage = App.getMainStage();
            Stage newStage = new Stage();

            FXMLLoader loader = new FXMLLoader(Viewer.class.getResource(caminho));
            Parent novaTela = loader.load();

            Object controller = loader.getController();
            if (configuracaoController != null) {
                try {
                    T castedController = (T) controller;
                    configuracaoController.accept(castedController);
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("O controlador carregado não corresponde ao tipo esperado.");
                }
            }

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


    public static void loadViewCadastroCategoria(String caminho, CategoriaObserver categoriaObserver, CategoriaDTO categoriaDTO) {
        Viewer.loadView(caminho, controller -> {
            if (controller instanceof CadastroCategoriaController cadastroCategoriaController) {
                cadastroCategoriaController.adicionarObserver(categoriaObserver);
                if (categoriaDTO != null) {
                    cadastroCategoriaController.setCategoria(categoriaDTO);
                }
            }
        });
    }

    public static void loadViewCadastroProduto(String caminho, ProdutoController produtoController, ProdutoDTO produtoDTO) {
        Viewer.loadView(caminho, controller -> {
            if (controller instanceof CadastroProdutoController cadastroProdutoController) {
                cadastroProdutoController.adicionarObserver(produtoController);
                if (produtoDTO !=null) {
                    cadastroProdutoController.atualizarProduto(produtoDTO);
                }
            }

        });
    }

    public static void loadViewCadastroFornecedor(String caminho, FornecedorController fornecedorController, FornecedorDTO fornecedorDTO) {
        Viewer.loadView(caminho, controller -> {
            if (controller instanceof CadastroFornecedorController cadastroFornecedorController) {
                cadastroFornecedorController.adicionarObserver(fornecedorController);
                if (fornecedorDTO !=null) {
                    cadastroFornecedorController.atualizarFornecedor(fornecedorDTO);
                }
            }

        });
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
}
