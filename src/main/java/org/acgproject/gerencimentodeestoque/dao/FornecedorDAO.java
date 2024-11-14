package org.acgproject.gerencimentodeestoque.dao;

import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;

import java.util.List;

public interface FornecedorDAO {
    void inserirFornecedor(FornecedorDTO fornecedor);
    void alterarFornecedor(FornecedorDTO fornecedor);
    void excluirFornecedor(Integer id);
    FornecedorDTO consultarFornecedor(Integer id);
    List<FornecedorDTO> listarFornecedor();
}
