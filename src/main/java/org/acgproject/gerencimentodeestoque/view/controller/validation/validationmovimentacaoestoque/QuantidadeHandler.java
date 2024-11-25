package org.acgproject.gerencimentodeestoque.view.controller.validation.validationmovimentacaoestoque;

import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.MovimentacaoEstoqueHandler;

public class QuantidadeHandler extends MovimentacaoEstoqueHandler {

    public MovimentacaoEstoqueDTO handle(MovimentacaoEstoqueDTO movimentacaoEstoque) {
        if (movimentacaoEstoque.getQuantidade() == null || movimentacaoEstoque.getQuantidade() <= 0) {
            throw new ValidacaoException("Digite uma quantidade maior que 0!");
        }else
            return super.handle(movimentacaoEstoque);
    }
}
