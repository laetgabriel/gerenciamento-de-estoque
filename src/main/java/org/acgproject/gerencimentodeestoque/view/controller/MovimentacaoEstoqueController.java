package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.acgproject.gerencimentodeestoque.utils.Viewer;

import java.net.URL;
import java.util.ResourceBundle;

public class MovimentacaoEstoqueController implements Initializable {

    @FXML
    private MenuItem movimentacaoEstoque;

    @FXML
    private MenuItem produto;

    @FXML
    private MenuItem fornecedor;

    @FXML
    private MenuItem categoria;


    @FXML
    public void onMenuItemMovimentacaoEstoque(){
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/MovimentacaoEstoque.fxml");
    }

    @FXML
    public void onMenuItemProduto(){
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/Produto.fxml");
    }

    @FXML
    public void onMenuItemFornecedor(){
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/Fornecedor.fxml");
    }

    @FXML
    public void onMenuItemCategoria(){
        Viewer.loadView("/org/acgproject/gerencimentodeestoque/view/Categoria.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
