package org.acgproject.gerencimentodeestoque.controller;

import jakarta.persistence.PersistenceException;
import org.acgproject.gerencimentodeestoque.dao.FornecedorDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.FornecedorDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;

import java.util.List;

public class FornecedorController {

    private FornecedorDAO fornecedorDAO = new FornecedorDAOImpl();

    public void inserirFornecedor(FornecedorDTO fornecedor) throws PersistenceException {
        try {
            fornecedorDAO.inserirFornecedor(fornecedor) ;
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }

    public void atualizarFornecedor(FornecedorDTO fornecedor) throws PersistenceException {
        try {
            fornecedorDAO.alterarFornecedor(fornecedor);
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }
    public void excluirFornecedor(int id) throws PersistenceException {
        try {
            fornecedorDAO.excluirFornecedor(id);
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }
    public FornecedorDTO consultarFornecedorPorNome(String nome){return fornecedorDAO.consultarFornecedorPorNome(nome);}
    public List<FornecedorDTO> listarTodosOsFornecedores() { return fornecedorDAO.listarTodosOsFornecedores();}
}
