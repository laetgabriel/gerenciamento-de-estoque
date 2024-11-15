package org.acgproject.gerencimentodeestoque.view.controller.validation.validationfornecedor;


import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.FornecedorHandler;

public class NomeFornecedorHandler extends FornecedorHandler {
    @Override
    public FornecedorDTO handle(FornecedorDTO fornecedorDTO) throws ValidacaoException {
        if (fornecedorDTO == null || fornecedorDTO.getNome() == null || fornecedorDTO.getNome().isEmpty()) {
            throw new ValidacaoException("Digite um nome!");
        } else
            return super.handle(fornecedorDTO);
    }
}


