package org.acgproject.gerencimentodeestoque.view.controller.validation.validationmovimentacaoestoque;

import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.model.entities.MovimentacaoEstoque;
import org.acgproject.gerencimentodeestoque.view.controller.exceptions.ValidacaoException;
import org.acgproject.gerencimentodeestoque.view.controller.validation.FornecedorHandler;
import org.acgproject.gerencimentodeestoque.view.controller.validation.MovimentacaoEstoqueHandler;

public class MovimentacaoEstoqueValidator {
    private final MovimentacaoEstoqueHandler firstHandler;

    public MovimentacaoEstoqueValidator( ) {
        MovimentacaoEstoqueHandler produtoHandler = new ProdutoHandler();
        MovimentacaoEstoqueHandler tipoHandler = new TipoHandler();
        MovimentacaoEstoqueHandler quantidadeHandler = new QuantidadeHandler();
        MovimentacaoEstoqueHandler dataHandler = new DataHandler();

        produtoHandler.setNextHandler(tipoHandler)
                .setNextHandler(quantidadeHandler)
                .setNextHandler(dataHandler);

        this.firstHandler = produtoHandler;
    }

    public void validarMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoque) throws ValidacaoException {
        if (firstHandler != null) {
            firstHandler.handle(movimentacaoEstoque);
        }
    }
}
