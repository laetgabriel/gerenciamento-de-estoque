package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.CategoriaDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroCategoriaController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private TextField txtNome;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblErroNomeCategoria;
    @FXML
    private Label lblErroDescricaoCategoria;

    private final CategoriaDAO categoriaDAO = new CategoriaDAOImpl();

    public void onBtnSalvar() {

        lblErroNomeCategoria.setText("");
        lblErroDescricaoCategoria.setText("");
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();

        CategoriaDTO categoriaDTO = new CategoriaDTO(null, nome, descricao);
        categoriaDAO.inserirCategoria(categoriaDTO);

        lblErroNomeCategoria.setText("");
        lblErroDescricaoCategoria.setText("");
    }

    public void onBtnCancelar(){
        Stage palco = (Stage) btnCancelar.getScene().getWindow();
        palco.close();
    }
}
