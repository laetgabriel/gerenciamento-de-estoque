package org.acgproject.gerencimentodeestoque.view.controller.validation.validationfornecedor;

import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.FornecedorHandler;

public class FornecedorValidator {
    private final FornecedorHandler firstHandler;

    public FornecedorValidator() {
        FornecedorHandler nomeFornecedorHandler = new NomeFornecedorHandler();
        FornecedorHandler telefoneFornecedorHandler = new TelefoneFornecedorHandler();
        FornecedorHandler emailFornecedorHandler = new EmailFornecedorHandler();

        nomeFornecedorHandler
                .setNextHandler(telefoneFornecedorHandler)
                .setNextHandler(emailFornecedorHandler);

        this.firstHandler = nomeFornecedorHandler;
    }

    public void validarFornecedor(FornecedorDTO fornecedorDTO) throws ValidacaoException {
        if (firstHandler != null) {
            firstHandler.handle(fornecedorDTO);
        }
    }

}
