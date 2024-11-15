package org.acgproject.gerencimentodeestoque.view.controller.validation.validationfornecedor;

import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.FornecedorHandler;

public class TelefoneFornecedorHandler extends FornecedorHandler {

    @Override
    public FornecedorDTO handle(FornecedorDTO fornecedorDTO){
        if(fornecedorDTO.getTelefone() == null || fornecedorDTO.getTelefone().length() != 11){
            throw new ValidacaoException("Digite um telefone válido!");
        }else
          return super.handle(fornecedorDTO);
    }
}
