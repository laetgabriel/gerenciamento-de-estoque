package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.acgproject.gerencimentodeestoque.controller.FornecedorController;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.utils.Viewer;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.validationfornecedor.FornecedorValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroFornecedorController implements Initializable {

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


    @Override
    public void initialize(URL url, ResourceBundle resources) {
        lblErroNome.setVisible(false);
        lblErroTelefone.setVisible(false);
        lblErroEmail.setVisible(false);
    }

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

        FornecedorDTO fornecedorDTO = new FornecedorDTO(
                null,
                nomeFornecedor,
                telefoneFornecedor,
                emailFornecedor
        );
        try {
            fornecedorValidator.validarFornecedor(fornecedorDTO);
            fornecedorController.inserirFornecedor(fornecedorDTO);
            Alertas.mostrarAlerta("Sucesso", "Fornecedor salvo com sucesso!", Alert.AlertType.INFORMATION);
            Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/fornecedor.fxml");
        } catch (ValidacaoException e) {
            if (e.getMessage().contains("Nome")) {
                lblErroNome.setText(e.getMessage());
                lblErroNome.setVisible(true);
            } else if (e.getMessage().contains("Telefone")) {
                lblErroTelefone.setText(e.getMessage());
                lblErroTelefone.setVisible(true);
            } else if (e.getMessage().contains("Email")) {
                lblErroEmail.setText(e.getMessage());
                lblErroEmail.setVisible(true);
            }
        }
    }


    public void cancelarCadastro() {
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/fornecedor.fxml");
    }
}