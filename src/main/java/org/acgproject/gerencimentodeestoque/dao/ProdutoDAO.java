package org.acgproject.gerencimentodeestoque.dao;

import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;

import java.util.List;

public interface ProdutoDAO {

    void inserirProduto(ProdutoDTO produtoDTO);
    void alterarProduto(ProdutoDTO produtoDTO);
    void excluirProduto(Integer id);
    ProdutoDTO buscarProduto(Integer id);
    List<ProdutoDTO> listarProdutos();

}
