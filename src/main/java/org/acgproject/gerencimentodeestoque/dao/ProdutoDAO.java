package org.acgproject.gerencimentodeestoque.dao;

import jakarta.persistence.PersistenceException;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;

import java.util.List;

public interface ProdutoDAO {

    void inserirProduto(ProdutoDTO produtoDTO);
    void alterarProduto(ProdutoDTO produtoDTO);
    void excluirProduto(Integer id) throws PersistenceException;
    ProdutoDTO buscarProduto(Integer id);
    ProdutoDTO buscarProdutoPorNome(String nome);
    List<ProdutoDTO> listarProdutos();
    List<String> listarCategorias();
    List<String> listarFornecedores();

}
