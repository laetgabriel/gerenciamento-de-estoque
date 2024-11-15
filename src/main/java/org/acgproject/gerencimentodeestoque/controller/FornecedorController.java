package org.acgproject.gerencimentodeestoque.controller;

import org.acgproject.gerencimentodeestoque.dao.FornecedorDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.FornecedorDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;

import java.util.List;

public class FornecedorController {

    private FornecedorDAO fornecedorDAO = new FornecedorDAOImpl();

    public FornecedorDTO consultarFornecedorPorNome(String nome){return fornecedorDAO.consultarFornecedorPorNome(nome);}
    public List<FornecedorDTO> listarTodosOsFornecedores() { return fornecedorDAO.listarTodosOsFornecedores();}
}
