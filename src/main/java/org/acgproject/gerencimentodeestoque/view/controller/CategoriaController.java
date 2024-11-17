package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.utils.Viewer;
import org.acgproject.gerencimentodeestoque.view.observer.CategoriaObserver;
import org.hibernate.annotations.View;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoriaController implements Initializable, CategoriaObserver {

    private org.acgproject.gerencimentodeestoque.controller.CategoriaController categoriaController;

    @FXML
    private Button btnNovo;
    @FXML
    private Button btnAtualizar;
    @FXML
    private TableView<CategoriaDTO> tblCategoria;
    @FXML
    private TableColumn<CategoriaDTO, Integer> colId;
    @FXML
    private TableColumn<CategoriaDTO, String> colNome;
    @FXML
    private TableColumn<CategoriaDTO, String> colDescricao;

    private ObservableList<CategoriaDTO> categoriaObservableList;

    private void initializeNodes(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        colId.setStyle("-fx-alignment: CENTER;");
        colNome.setStyle("-fx-alignment: CENTER;");
        colDescricao.setStyle("-fx-alignment: CENTER;");
    }

    @FXML
    private void btnNovaCategoria() {
        Viewer.loadViewCadastroCategoria("/org/acgproject/gerencimentodeestoque/view/CadastroCategoria.fxml", this);
    }



    public void btnAtualizarCategoria() {
        CategoriaDTO categoria = tblCategoria.getSelectionModel().getSelectedItem();
        if(categoria != null){
            Viewer.loadViewDetalharCategoria("/org/acgproject/gerencimentodeestoque/view/CadastroCategoria.fxml", this, categoria);
        }else
            Alertas.mostrarAlerta("Erro", "Selecione uma categoria para atualizar", Alert.AlertType.ERROR);
    }

    @Override
    public void atualizarCategorias() {
        if(categoriaController == null){
            throw new IllegalStateException("categoriaController == null");
        }
        List<CategoriaDTO> categorias = categoriaController.listarCategorias();
        categoriaObservableList = FXCollections.observableArrayList(categorias);
        tblCategoria.setItems(categoriaObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriaController = new org.acgproject.gerencimentodeestoque.controller.CategoriaController();
        initializeNodes();
        atualizarCategorias();
    }
}
