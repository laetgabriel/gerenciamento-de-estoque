package org.acgproject.gerencimentodeestoque.controller;

import org.acgproject.gerencimentodeestoque.dao.FornecedorDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.FornecedorDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Fornecedor;

import java.util.List;

public class FornecedorController {

    private FornecedorDAO fornecedorDAO = new FornecedorDAOImpl();
    public void inserirFornecedor(FornecedorDTO fornecedorDTO) {
        fornecedorDAO.inserirFornecedor(fornecedorDTO);
    }
    public FornecedorDTO consultarFornecedorPorNome(String nome){return fornecedorDAO.consultarFornecedorPorNome(nome);}
    public List<FornecedorDTO> listarTodosOsFornecedores() { return fornecedorDAO.listarTodosOsFornecedores();}
}
