package org.acgproject.gerencimentodeestoque.view.controller.validation.validationmovimentacaoestoque;

import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.MovimentacaoEstoqueHandler;

public class ProdutoHandler extends MovimentacaoEstoqueHandler {

    public MovimentacaoEstoqueDTO handle(MovimentacaoEstoqueDTO movimentacaoEstoque) {
        if (movimentacaoEstoque == null || movimentacaoEstoque.getProdutoDTO() == null || movimentacaoEstoque.getProdutoDTO().getNome().isEmpty()) {
            throw new ValidacaoException("Selecione um produto!");
        }else
            return super.handle(movimentacaoEstoque);
    }
}
