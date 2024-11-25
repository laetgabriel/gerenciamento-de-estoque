package org.acgproject.gerencimentodeestoque.view.controller.validation.validationmovimentacaoestoque;

import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.MovimentacaoEstoqueHandler;

public class DataHandler extends MovimentacaoEstoqueHandler {

    public MovimentacaoEstoqueDTO handle(MovimentacaoEstoqueDTO movimentacaoEstoque) {
        if (movimentacaoEstoque.getData() == null|| movimentacaoEstoque.getData().toString().isEmpty()) {
            throw new ValidacaoException("Selecione/digite uma data!");
        }else
            return super.handle(movimentacaoEstoque);
    }
}
