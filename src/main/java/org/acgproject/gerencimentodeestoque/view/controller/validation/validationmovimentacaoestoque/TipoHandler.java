package org.acgproject.gerencimentodeestoque.view.controller.validation.validationmovimentacaoestoque;

import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.MovimentacaoEstoqueHandler;

public class TipoHandler extends MovimentacaoEstoqueHandler {

    public MovimentacaoEstoqueDTO handle(MovimentacaoEstoqueDTO movimentacaoEstoque) {
        if (movimentacaoEstoque.getTipoMovimentacao() == null || movimentacaoEstoque.getTipoMovimentacao().toString().isEmpty()) {
            throw new ValidacaoException("Selecione um tipo!");
        }else
            return super.handle(movimentacaoEstoque);
    }
}
