package org.acgproject.gerencimentodeestoque.view.controller.validation;

import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;

public abstract class FornecedorHandler {

    protected FornecedorHandler nextHandler;

    public FornecedorDTO handle(FornecedorDTO fornecedorDTO) throws ValidacaoException {
        if (nextHandler != null) {
            return nextHandler.handle(fornecedorDTO);
        }
        return fornecedorDTO;
    }

    public FornecedorHandler setNextHandler(FornecedorHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }
}