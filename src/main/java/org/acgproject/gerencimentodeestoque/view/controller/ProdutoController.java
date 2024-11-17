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
import org.acgproject.gerencimentodeestoque.utils.Viewer;
import org.acgproject.gerencimentodeestoque.view.observer.ProdutoObserver;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
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

    private final org.acgproject.gerencimentodeestoque.controller.ProdutoController produtoController = new org.acgproject.gerencimentodeestoque.controller.ProdutoController();
    private final org.acgproject.gerencimentodeestoque.controller.CategoriaController categoriaController = new CategoriaController();
    private final org.acgproject.gerencimentodeestoque.controller.FornecedorController fornecedorController = new FornecedorController();
    private ObservableList<CategoriaDTO> categorias;
    private ObservableList<FornecedorDTO> fornecedores;

    @FXML
    public void onBtnNovo() {
        Viewer.loadViewCadastro("/org/acgproject/gerencimentodeestoque/view/CadastroProduto.fxml");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
        atualizarProdutos();
        List<CategoriaDTO> listaCategoria = categoriaController.listarCategorias();
        categorias = FXCollections.observableArrayList(listaCategoria);
        comboBoxCategoria.setItems(categorias);

        List<FornecedorDTO> listaFornecedores = fornecedorController.listarTodosOsFornecedores();
        fornecedores = FXCollections.observableArrayList(listaFornecedores);
        comboBoxFornecedor.setItems(fornecedores);
    }

}
