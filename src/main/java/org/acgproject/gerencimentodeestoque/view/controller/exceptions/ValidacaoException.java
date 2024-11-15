package org.acgproject.gerencimentodeestoque.view.controller.exceptions;

public class ValidacaoException extends Exception {

    public ValidacaoException(){
        super();
    }

    public ValidacaoException(String mensagem){
        super(mensagem);
    }
}
