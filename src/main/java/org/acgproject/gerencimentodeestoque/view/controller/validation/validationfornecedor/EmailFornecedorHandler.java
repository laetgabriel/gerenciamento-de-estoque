package org.acgproject.gerencimentodeestoque.view.controller.validation.validationfornecedor;

import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.FornecedorHandler;

public class EmailFornecedorHandler extends FornecedorHandler {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public FornecedorDTO handle(FornecedorDTO fornecedorDTO) throws ValidacaoException {
        if (!fornecedorDTO.getEmail().matches(EMAIL_REGEX) || fornecedorDTO.getEmail().isEmpty() || fornecedorDTO.getEmail() == null) {
            throw new ValidacaoException("Digite um email válido!");
        }
        return super.handle(fornecedorDTO);
    }

}