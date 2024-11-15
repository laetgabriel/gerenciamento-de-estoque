package org.acgproject.gerencimentodeestoque.controller;

import org.acgproject.gerencimentodeestoque.dao.impl.FornecedorDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;

import java.util.regex.Pattern;

public class FornecedorController {

    private FornecedorDAOImpl fornecedorDAO;
    private final Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    public FornecedorController() {
        this.fornecedorDAO = new FornecedorDAOImpl();
    }

    public boolean validarEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public boolean validarTelefone(String telefone) {
        return telefone != null && telefone.length() == 11 && telefone.matches("\\d+");
    }

    public boolean validarNome(String nome) {
        return nome != null && !nome.trim().isEmpty();
    }

    public void inserirFornecedor(FornecedorDTO fornecedorDTO) {
        fornecedorDAO.inserirFornecedor(fornecedorDTO);
    }
}
