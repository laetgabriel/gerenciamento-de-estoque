package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.controller.CategoriaController;
import org.acgproject.gerencimentodeestoque.controller.FornecedorController;
import org.acgproject.gerencimentodeestoque.controller.ProdutoController;
import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.ProdutoDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;
import org.acgproject.gerencimentodeestoque.model.entities.Fornecedor;
import org.acgproject.gerencimentodeestoque.utils.Alertas;
import org.acgproject.gerencimentodeestoque.utils.Restricoes;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoCadastrosException;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class CadastroProdutoController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPreco;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private ComboBox comboBoxCategoria;
    @FXML
    private ComboBox comboBoxFornecedor;
    @FXML
    private DatePicker data;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblErroNome;
    @FXML
    private Label lblErroPreco;
    @FXML
    private Label lblErroQuantidade;
    @FXML
    private Label lblErroCategoria;
    @FXML
    private Label lblErroFornecedor;
    @FXML
    private Label lblErroData;

    private final org.acgproject.gerencimentodeestoque.controller.ProdutoController produtoController = new ProdutoController();
    private final CategoriaController categoriaController = new CategoriaController();
    private final FornecedorController fornecedorController = new FornecedorController();
    private ObservableList<CategoriaDTO> categorias;
    private ObservableList<FornecedorDTO> fornecedores;

    @FXML
    public void onBtnSalvar() {
        limpaLblErros();
        try{
            ProdutoDTO produtoDTO = getDados();
            produtoController.inserirProduto(produtoDTO);
            Alertas.mostrarAlerta("Sucesso", "Produto salvo com sucesso!", Alert.AlertType.INFORMATION);
            Stage palco = (Stage) btnSalvar.getScene().getWindow();
            palco.close();
        }catch (ValidacaoCadastrosException e){
            setLblErros(e.getErrors());
        }catch (RuntimeException e){
            Alertas.mostrarAlerta("ERRO", "Produto já cadastrado!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onBtnCancelar() {
        Stage palco = (Stage) btnCancelar.getScene().getWindow();
        palco.close();
    }

    private ProdutoDTO getDados() throws ValidacaoCadastrosException {
        ValidacaoCadastrosException erros = new ValidacaoCadastrosException();
        String nome = txtNome.getText();
        String preco = txtPreco.getText();
        String quantidade = txtQuantidade.getText();
        LocalDate localDate = data.getValue();

        if (nome == null || nome.trim().isEmpty()){
            erros.addErro("nome", "Digite um nome!");
        }

        if (preco == null || preco.trim().isEmpty()){
            erros.addErro("preco", "Digite um preço!");
        }else if( new BigDecimal(preco).scale() > 2){
            erros.addErro("preco", "Apenas duas casas decimais!");
        }

        if (quantidade == null || quantidade.trim().isEmpty()){
            erros.addErro("quantidade", "Digite uma quantidade!");
        }

        if(comboBoxCategoria.getSelectionModel().getSelectedItem() == null){
            erros.addErro("categoria", "Selecione uma categoria!");
        }

        if(comboBoxFornecedor.getSelectionModel().getSelectedItem() == null){
            erros.addErro("fornecedor", "Selecione um fornecedor!");
        }

        if(data.getValue() == null){
            erros.addErro("data", "Digite uma data!");
        }

        if (!erros.getErrors().isEmpty()){
            throw erros;
        }

        CategoriaDTO categoriaDTO =
                categoriaController.buscarCategoriaPorNome(comboBoxCategoria.getSelectionModel().getSelectedItem().toString());

        FornecedorDTO fornecedorDTO =
                fornecedorController.consultarFornecedorPorNome(comboBoxFornecedor.getSelectionModel().getSelectedItem().toString());
        ProdutoDTO produtoDTO  = new ProdutoDTO(nome,new BigDecimal(preco), Integer.parseInt(quantidade), data.getValue(),
                fornecedorDTO, categoriaDTO);

        return produtoDTO;
    }

    private void setLblErros(Map<String, String> erros){
        Set<String> campos = erros.keySet();

        if(campos.contains("nome")){
            lblErroNome.setText(erros.get("nome"));
        }

        if(campos.contains("preco")){
            lblErroPreco.setText(erros.get("preco"));
        }

        if (campos.contains("quantidade")){
            lblErroQuantidade.setText(erros.get("quantidade"));
        }

        if (campos.contains("categoria")){
            lblErroCategoria.setText(erros.get("categoria"));
        }

        if (campos.contains("fornecedor")){
            lblErroFornecedor.setText(erros.get("fornecedor"));
        }

        if (campos.contains("data")){
            lblErroData.setText(erros.get("data"));
        }
    }

    private void limpaLblErros(){
        lblErroNome.setText("");
        lblErroPreco.setText("");
        lblErroQuantidade.setText("");
        lblErroCategoria.setText("");
        lblErroFornecedor.setText("");
        lblErroData.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<CategoriaDTO> listaCategoria = categoriaController.listarCategorias();
        categorias = FXCollections.observableArrayList(listaCategoria);
        comboBoxCategoria.setItems(categorias);

        List<FornecedorDTO> listaFornecedores = fornecedorController.listarTodosOsFornecedores();
        fornecedores = FXCollections.observableArrayList(listaFornecedores);
        comboBoxFornecedor.setItems(fornecedores);

        Restricoes.setTextFieldDouble(txtPreco);
        Restricoes.setTextFieldInteger(txtQuantidade);
        Restricoes.setDatePickerValidation(data);
    }
}
