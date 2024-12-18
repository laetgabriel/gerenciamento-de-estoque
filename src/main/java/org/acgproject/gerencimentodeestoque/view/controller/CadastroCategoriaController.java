package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.controller.CategoriaController;
import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.CategoriaDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.observer.CategoriaObserver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroCategoriaController implements Initializable {

    @FXML
    private Label titulo;

    private CategoriaDTO categoriaAtual;
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
    private List<CategoriaObserver> observers = new ArrayList<>();

    public void onBtnSalvar() {
        lblErroNomeCategoria.setText("");
        lblErroDescricaoCategoria.setText("");

        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();

        try {
            validarCategoria(nome);
            if (categoriaAtual == null) {
                CategoriaDTO novaCategoria = new CategoriaDTO(null, nome, descricao);

                categoriaDAO.inserirCategoria(novaCategoria);
                Alertas.mostrarAlerta("Sucesso", "Categoria salva com sucesso!", Alert.AlertType.INFORMATION);
            } else {
                categoriaAtual.setNome(nome);
                categoriaAtual.setDescricao(descricao);
                categoriaDAO.alterarCategoria(categoriaAtual);
                Alertas.mostrarAlerta("Sucesso", "Categoria atualizada com sucesso!", Alert.AlertType.INFORMATION);
            }

            txtNome.clear();
            txtDescricao.clear();
            categoriaAtual = null;
            notificarOuvintes();

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
        if (categoriaAtual == null || !categoriaAtual.getNome().equals(nome)) {
            if (categoriaDAO.nomeCategoriaExiste(nome)) {
                throw new ValidacaoException("Nome de categoria já existe");
            }
        }
    }


    public void adicionarObserver(CategoriaObserver observer) {
        observers.add(observer);
    }

    private void notificarOuvintes(){
        for (CategoriaObserver observer : observers) {
            observer.atualizarCategorias();
        }
    }

    public void setCategoria(CategoriaDTO categoria) {
        titulo.setText("Atualizar Categoria");
        this.categoriaAtual = categoria;
        if (categoria != null) {
            txtNome.setText(categoria.getNome());
            txtDescricao.setText(categoria.getDescricao());
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
