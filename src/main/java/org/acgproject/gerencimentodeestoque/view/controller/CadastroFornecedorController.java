package org.acgproject.gerencimentodeestoque.view.controller;

import jakarta.persistence.PersistenceException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.controller.FornecedorController;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.utils.Restricoes;
import org.acgproject.gerencimentodeestoque.utils.Viewer;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.validationfornecedor.FornecedorValidator;
import org.acgproject.gerencimentodeestoque.view.observer.FornecedorObserver;
import org.acgproject.gerencimentodeestoque.view.observer.ProdutoObserver;

import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroFornecedorController implements Initializable {

    private FornecedorDTO fornecedorAtualizar = null;

    @FXML
    private Label titulo;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label lblErroNome;

    @FXML
    private Label lblErroTelefone;

    @FXML
    private Label lblErroEmail;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnCancelar;

    private org.acgproject.gerencimentodeestoque.controller.FornecedorController fornecedorController = new FornecedorController();
    private FornecedorValidator fornecedorValidator = new FornecedorValidator();

    private List<FornecedorObserver> observers = new ArrayList<>();

    @FXML
    public void salvarFornecedor() {
        lblErroNome.setText("");
        lblErroTelefone.setText("");
        lblErroEmail.setText("");

        txtNome.getStyleClass().remove("error");
        txtTelefone.getStyleClass().remove("error");
        txtEmail.getStyleClass().remove("error");

        String nomeFornecedor = txtNome.getText();
        String telefoneFornecedor = txtTelefone.getText();
        String emailFornecedor = txtEmail.getText();

        if(emailFornecedor != null && emailFornecedor.isEmpty()){
            emailFornecedor = null;
        }

        FornecedorDTO fornecedorDTO = new FornecedorDTO(
                null,
                nomeFornecedor,
                telefoneFornecedor,
                emailFornecedor
        );
        try {
            fornecedorValidator.validarFornecedor(fornecedorDTO);
            if (fornecedorAtualizar == null) {
                fornecedorController.inserirFornecedor(fornecedorDTO);
                Alertas.mostrarAlerta("Sucesso", "Fornecedor salvo com sucesso!", Alert.AlertType.INFORMATION);
            }else {
                fornecedorAtualizar.setNome(nomeFornecedor);
                fornecedorAtualizar.setTelefone(telefoneFornecedor);
                fornecedorAtualizar.setEmail(emailFornecedor);
                fornecedorController.atualizarFornecedor(fornecedorAtualizar);
                Alertas.mostrarAlerta("Sucesso", "Fornecedor atualizado com sucesso!", Alert.AlertType.INFORMATION);
            }
            notificarOuvintes();
            Stage palco = (Stage) btnCancelar.getScene().getWindow();
            palco.close();
        } catch (ValidacaoException e) {
            if (e.getMessage().contains("nome")) {
                lblErroNome.setText(e.getMessage());
                lblErroNome.setVisible(true);
            } else if (e.getMessage().contains("telefone")) {
                lblErroTelefone.setText(e.getMessage());
                lblErroTelefone.setVisible(true);
            } else if (e.getMessage().contains("email")) {
                lblErroEmail.setText(e.getMessage());
                lblErroEmail.setVisible(true);
            }
        } catch (PersistenceException e){
            Alertas.mostrarAlerta("Erro", "Nome, telefone ou email já cadastrados no sistema!", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void cancelarCadastro() {
        Stage palco = (Stage) btnCancelar.getScene().getWindow();
        palco.close();
    }

    public void atualizarFornecedor(FornecedorDTO fornecedorDTO) {
        fornecedorAtualizar = fornecedorDTO;

        titulo.setText("Atualizar Fornecedor");

        txtNome.setText(fornecedorDTO.getNome());
        txtTelefone.setText(fornecedorDTO.getTelefone());
        txtEmail.setText(fornecedorDTO.getEmail());
    }

    public void adicionarObserver(FornecedorObserver observer) {
        observers.add(observer);
    }

    private void notificarOuvintes(){
        for (FornecedorObserver observer : observers) {
            observer.atualizarFornecedores();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        lblErroNome.setVisible(false);
        lblErroTelefone.setVisible(false);
        lblErroEmail.setVisible(false);

        Restricoes.setTextFieldInteger(txtTelefone);
    }

}