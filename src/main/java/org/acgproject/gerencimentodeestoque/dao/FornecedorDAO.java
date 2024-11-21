package org.acgproject.gerencimentodeestoque.dao;

import jakarta.persistence.PersistenceException;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;

import java.util.List;

public interface FornecedorDAO {
    void inserirFornecedor(FornecedorDTO fornecedor) throws PersistenceException;
    void alterarFornecedor(FornecedorDTO fornecedor) throws PersistenceException;
    void excluirFornecedor(Integer id) throws PersistenceException;
    FornecedorDTO consultarFornecedor(Integer id);
    FornecedorDTO consultarFornecedorPorNome(String nome);
    List<FornecedorDTO> listarTodosOsFornecedores();
}
