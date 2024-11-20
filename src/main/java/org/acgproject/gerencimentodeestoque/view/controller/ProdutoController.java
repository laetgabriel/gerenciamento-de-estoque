package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    }

    private void initializeNodes(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));

    }

    @Override
    public void atualizarProdutos() {
        if(produtoController == null){
            Alertas.mostrarAlerta("ERRO", "Erro ao carregar tela de produtos!", Alert.AlertType.INFORMATION);
        }
        List<ProdutoDTO> produtoDTOS = produtoController.listarProdutos();
        produtoDTOObservableList = FXCollections.observableArrayList(produtoDTOS);
        tblProdutos.setItems(produtoDTOObservableList);
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
        AtualizarVisaoTabelas.tabelaFiltradaProduto(filtroFornecedor, filtroNomeProduto, filtroCategoria,
                produtoDTOObservableList, tblProdutos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
        atualizarProdutos();
        List<String> listaCategoria = produtoController.listarCategorias();
        categorias = FXCollections.observableArrayList(listaCategoria);
        categorias.add("Sem categoria");
        comboBoxCategoria.setItems(categorias);


        List<String> listaFornecedores = produtoController.listarFornecedores();
        fornecedores = FXCollections.observableArrayList(listaFornecedores);
        fornecedores.add("Sem fornecedor");
        comboBoxFornecedor.setItems(fornecedores);

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
