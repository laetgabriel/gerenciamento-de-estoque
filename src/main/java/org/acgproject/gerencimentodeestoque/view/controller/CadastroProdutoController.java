package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.ProdutoDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;
import org.acgproject.gerencimentodeestoque.model.entities.Fornecedor;

import java.time.LocalDate;
import java.util.Date;

public class CadastroProdutoController {

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
    private DatePicker dataPicker;
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

    private final ProdutoDAO produtoDAO = new ProdutoDAOImpl();

    public void salvarProduto(){
        lblErroNome.setText("");
        lblErroPreco.setText("");
        lblErroQuantidade.setText("");
        lblErroCategoria.setText("");
        lblErroFornecedor.setText("");
        lblErroData.setText("");

        String nome = txtNome.getText();
        String preco = txtPreco.getText();
        String quantidade = txtQuantidade.getText();
        CategoriaDTO categoria = (CategoriaDTO) comboBoxCategoria.getValue();
        Fornecedor fornecedor = (Fornecedor) comboBoxFornecedor.getValue();
        LocalDate localDate = dataPicker.getValue();


    }
}
