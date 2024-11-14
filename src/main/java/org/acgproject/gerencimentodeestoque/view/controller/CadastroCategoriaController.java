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
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroCategoriaController {

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

        try {
            validarCategoria(nome);
            CategoriaDTO categoriaDTO = new CategoriaDTO(null, nome, descricao);
            categoriaDAO.inserirCategoria(categoriaDTO);

            txtNome.clear();
            txtDescricao.clear();
            lblErroNomeCategoria.setText("");
            lblErroDescricaoCategoria.setText("");

            Stage palco = (Stage) btnSalvar.getScene().getWindow();
            palco.close();
        } catch (ValidacaoException e) {
            lblErroNomeCategoria.setText(e.getMessage());
        }
    }

    public void onBtnCancelar() {
        Stage palco = (Stage) btnCancelar.getScene().getWindow();
        palco.close();
    }

    private void validarCategoria(String nome) throws ValidacaoException {
        if (nome == null || nome.isEmpty() || nome.length() >= 255) {
            throw new ValidacaoException("Nome inválido");
        }
        if (categoriaDAO.nomeCategoriaExiste(nome)) {
            throw new ValidacaoException("Nome de categoria já existe");
        }
    }
}
