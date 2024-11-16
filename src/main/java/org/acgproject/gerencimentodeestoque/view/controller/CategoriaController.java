package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;
import org.acgproject.gerencimentodeestoque.utils.Viewer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoriaController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeNodes();
    }

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
        Viewer.loadViewCadastro("/org/acgproject/gerencimentodeestoque/view/CadastroCategoria.fxml");
    }

    public void setCategoriaController(org.acgproject.gerencimentodeestoque.controller.CategoriaController categoriaController) {
        this.categoriaController = categoriaController;
    }

    public void updateTableView(){
        if(categoriaController == null){
            throw new IllegalStateException("categoriaController == null");
        }
        List<CategoriaDTO> categorias = categoriaController.listarCategorias();
        categoriaObservableList = FXCollections.observableArrayList(categorias);
        tblCategoria.setItems(categoriaObservableList);
    }
}
