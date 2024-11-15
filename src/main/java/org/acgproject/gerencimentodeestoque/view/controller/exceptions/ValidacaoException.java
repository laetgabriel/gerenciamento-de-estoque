package org.acgproject.gerencimentodeestoque.view.controller.exceptions;

public class ValidacaoException extends RuntimeException {

    public ValidacaoException(){
        super();
    }

    public ValidacaoException(String mensagem){
        super(mensagem);
    }
}
