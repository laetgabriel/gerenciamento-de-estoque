package org.acgproject.gerencimentodeestoque.view.controller;

import jakarta.persistence.PersistenceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.utils.AtualizarVisaoTabelas;
import org.acgproject.gerencimentodeestoque.utils.Viewer;
import org.acgproject.gerencimentodeestoque.view.observer.FornecedorObserver;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FornecedorController implements Initializable, FornecedorObserver{

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnGerarRelatorio;

    @FXML
    private TextField txtNomeFornecedor;

    @FXML
    private TableView<FornecedorDTO> tblFornecedor;

    @FXML
    private TableColumn<FornecedorDTO, Integer> colId;
    @FXML
    private TableColumn<FornecedorDTO, String> colNome;
    @FXML
    private TableColumn<FornecedorDTO, String> colTelefone;
    @FXML
    private TableColumn<FornecedorDTO, String> colEmail;

    private ObservableList<FornecedorDTO> fornecedores;
    private FornecedorDTO fornecedorSelecionado;
    org.acgproject.gerencimentodeestoque.controller.FornecedorController fornecedorController = new org.acgproject.gerencimentodeestoque.controller.FornecedorController();

    @FXML
    public void onBtnNovo() {
        Viewer.loadViewCadastroFornecedor("/org/acgproject/gerencimentodeestoque/view/CadastroFornecedor.fxml", this, null);
    }

    @FXML
    public void onBtnAtualizar() {
        fornecedorSelecionado = tblFornecedor.getSelectionModel().getSelectedItem();
        if (fornecedorSelecionado != null) {
            Viewer.loadViewCadastroFornecedor("/org/acgproject/gerencimentodeestoque/view/CadastroFornecedor.fxml", this,
                    fornecedorSelecionado);
        } else {
            Alertas.mostrarAlerta("Erro", "Selecione um fornecedor para atualizar!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onBtnExcluir(){
        fornecedorSelecionado = tblFornecedor.getSelectionModel().getSelectedItem();
        try {

            if (fornecedorSelecionado != null) {
                Optional<ButtonType> escolha = Alertas.showConfirmation("Confirmação", "Tem certeza de que deseja excluir" +
                        " o fornecedor " + fornecedorSelecionado.getNome() + " ?");
                if (escolha.get() == ButtonType.OK) {
                    fornecedorController.excluirFornecedor(fornecedorSelecionado.getId());
                    atualizarFornecedores();
                }
            } else {
                Alertas.mostrarAlerta("Erro", "Selecione um fornecedor para excluir!", Alert.AlertType.ERROR);
            }

        } catch (PersistenceException e){
            Alertas.mostrarAlerta("Erro ao excluir", "Fornecedor relacionado com produto!", Alert.AlertType.ERROR);
        }
    }

    private void initializeNodes(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    public void atualizarFornecedores() {
        List<FornecedorDTO> produtoDTOS = fornecedorController.listarTodosOsFornecedores();
        fornecedores = FXCollections.observableArrayList(produtoDTOS);
        tblFornecedor.setItems(fornecedores);
    }

    @FXML
    public void onComboBoxFiltroChanged() {

        String filtroNomeFornecedor = txtNomeFornecedor.getText() != null
                ? txtNomeFornecedor.getText()
                : "";

        tabelaFiltrada(filtroNomeFornecedor);
    }

    private void tabelaFiltrada(String filtroFornecedor) {
        AtualizarVisaoTabelas.tabelaFiltradaFornecedor(filtroFornecedor,
                fornecedores, tblFornecedor);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
        atualizarFornecedores();

        txtNomeFornecedor.textProperty().addListener((observable, oldValue, newValue) -> {
            tabelaFiltrada(newValue);
        });
    }
}
