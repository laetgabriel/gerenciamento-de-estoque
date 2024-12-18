package org.acgproject.gerencimentodeestoque.view.controller;

import jakarta.persistence.PersistenceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.controller.CategoriaController;
import org.acgproject.gerencimentodeestoque.controller.FornecedorController;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.utils.AtualizarVisaoTabelas;
import org.acgproject.gerencimentodeestoque.utils.Viewer;
import org.acgproject.gerencimentodeestoque.view.observer.ProdutoObserver;


import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProdutoController implements Initializable, ProdutoObserver {


    @FXML
    private Button btnNovo;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnGerarRelatorio;

    @FXML
    private ComboBox comboBoxFornecedor;

    @FXML
    private TextField txtNomeProduto;

    @FXML
    private ComboBox comboBoxCategoria;

    @FXML
    private TableView<ProdutoDTO> tblProdutos;

    @FXML
    private TableColumn<ProdutoDTO, Integer> colId;
    @FXML
    private TableColumn<ProdutoDTO, String> colNome;
    @FXML
    private TableColumn<ProdutoDTO, BigDecimal> colPreco;
    @FXML
    private TableColumn<ProdutoDTO, Integer> colQuantidade;
    @FXML
    private TableColumn<ProdutoDTO, LocalDate> colDataCadastro;
    @FXML
    private TableColumn<ProdutoDTO, CategoriaDTO> colCategoria;
    @FXML
    private TableColumn<ProdutoDTO, FornecedorDTO> colFornecedor;

    private ObservableList<ProdutoDTO> produtoDTOObservableList;
    private ObservableList<ProdutoDTO> produtosFiltro;
    private ProdutoDTO produtoSelecionado;

    private final org.acgproject.gerencimentodeestoque.controller.ProdutoController produtoController = new org.acgproject.gerencimentodeestoque.controller.ProdutoController();
    private ObservableList<String> categorias;
    private ObservableList<String> fornecedores;

    @FXML
    public void onBtnNovo() {
        Viewer.loadViewCadastroProduto("/org/acgproject/gerencimentodeestoque/view/CadastroProduto.fxml", this, null);
    }

    public void onBtnAtualizar() {

        produtoSelecionado = tblProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            Viewer.loadViewCadastroProduto("/org/acgproject/gerencimentodeestoque/view/CadastroProduto.fxml", this,
                    produtoSelecionado);
        } else {
            Alertas.mostrarAlerta("Erro", "Selecione um produto para atualizar!", Alert.AlertType.ERROR);
        }

    }

    public void onBtnExcluir() {
        produtoSelecionado = tblProdutos.getSelectionModel().getSelectedItem();
        try {
            if (produtoSelecionado != null) {
                Optional<ButtonType> escolha = Alertas.showConfirmation("Confirmação", "Tem certeza de que deseja excluir" +
                        " o produto " + produtoSelecionado.getNome() + " ?");
                if (escolha.get() == ButtonType.OK) {
                    produtoController.excluirProduto(produtoSelecionado.getId());
                    atualizarProdutos();
                }
            } else {
                Alertas.mostrarAlerta("Erro", "Selecione um produto para excluir!", Alert.AlertType.ERROR);
            }
        } catch (PersistenceException e) {
            Alertas.mostrarAlerta("Erro ao excluir", "Produto relacionado com alguma  movimentação!",
                    Alert.AlertType.ERROR);
        }
    }

    public void onBtnGerarRelatorio() {
        Stage stage = (Stage) btnGerarRelatorio.getScene().getWindow();
        produtoController.gerarRelatorio(stage, produtosFiltro);
    }

    @Override
    public void atualizarProdutos() {
        List<ProdutoDTO> produtoDTOS = produtoController.listarProdutos();
        produtoDTOObservableList = FXCollections.observableArrayList(produtoDTOS);
        produtosFiltro = FXCollections.observableArrayList(produtoDTOS);
        tblProdutos.setItems(produtoDTOObservableList);
        carregarValoresComboBox();
    }

    @FXML
    public void onComboBoxFiltroChanged() {
        String filtroFornecedor = comboBoxFornecedor.getValue() != null
                ? comboBoxFornecedor.getValue().toString()
                : "";
        String filtroCategoria = comboBoxCategoria.getValue() != null
                ? comboBoxCategoria.getValue().toString()
                : "";

        String filtroNomeProduto = txtNomeProduto.getText() != null
                ? txtNomeProduto.getText()
                : "";

        tabelaFiltrada(filtroFornecedor, filtroNomeProduto, filtroCategoria);
    }

    private void tabelaFiltrada(String filtroFornecedor, String filtroNomeProduto, String filtroCategoria) {
        produtosFiltro = AtualizarVisaoTabelas.tabelaFiltradaProduto(filtroFornecedor, filtroNomeProduto, filtroCategoria,
                produtoDTOObservableList, tblProdutos);
    }

    private void initializeNodes(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        formatterColData();
    }

    private void formatterColData(){
        colDataCadastro.setCellFactory(column -> new TableCell<>() {
            private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(date.format(dateFormatter));
                }
            }
        });

    }

    private void carregarValoresComboBox(){
        List<String> listaCategoria = produtoController.listarCategorias();
        categorias = FXCollections.observableArrayList(listaCategoria);
        categorias.add("Sem categoria");
        comboBoxCategoria.setItems(categorias);


        List<String> listaFornecedores = produtoController.listarFornecedores();
        fornecedores = FXCollections.observableArrayList(listaFornecedores);
        fornecedores.add("Sem fornecedor");
        comboBoxFornecedor.setItems(fornecedores);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
        atualizarProdutos();
        carregarValoresComboBox();

        txtNomeProduto.textProperty().addListener((observable, oldValue, newValue) -> {
            String filtroFornecedor = comboBoxFornecedor.getValue() != null
                    ? comboBoxFornecedor.getValue().toString()
                    : "";
            String filtroCategoria = comboBoxCategoria.getValue() != null
                    ? comboBoxCategoria.getValue().toString()
                    : "";

            tabelaFiltrada(filtroFornecedor, newValue, filtroCategoria);
        });

    }

}
