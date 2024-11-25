package org.acgproject.gerencimentodeestoque.view.controller.validation;

import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;

public abstract class MovimentacaoEstoqueHandler {

    protected MovimentacaoEstoqueHandler nextHandler;

    public MovimentacaoEstoqueDTO handle(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) throws ValidacaoException {
        if (nextHandler != null) {
            return nextHandler.handle(movimentacaoEstoqueDTO);
        }
        return movimentacaoEstoqueDTO;
    }

    public MovimentacaoEstoqueHandler setNextHandler(MovimentacaoEstoqueHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }
}
