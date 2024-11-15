package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.acgproject.gerencimentodeestoque.utils.Viewer;

public class ProdutoController {

    @FXML
    private Button btnNovo;

    @FXML
    public void onBtnNovo() {
        Viewer.loadViewCadastro("/org/acgproject/gerencimentodeestoque/view/CadastroProduto.fxml");
    }

}
