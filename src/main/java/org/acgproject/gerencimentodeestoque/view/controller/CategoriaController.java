package org.acgproject.gerencimentodeestoque.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.acgproject.gerencimentodeestoque.utils.Viewer;

public class CategoriaController {

    @FXML
    private Button btnNovo;

    @FXML
    private void btnNovaCategoria() {
        Viewer.loadViewCadastro("/org/acgproject/gerencimentodeestoque/view/CadastroCategoria.fxml");
    }
}
