package org.acgproject.gerencimentodeestoque.controller;

import jakarta.persistence.PersistenceException;
import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.ProdutoDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;

import java.util.List;

public class ProdutoController {

    ProdutoDAO produtoDAO = new ProdutoDAOImpl();

    public void inserirProduto(ProdutoDTO produtoDTO){ produtoDAO.inserirProduto(produtoDTO);}
    public void alterarProduto(ProdutoDTO produtoDTO){ produtoDAO.alterarProduto(produtoDTO);}
    public void excluirProduto(Integer id) {
        try {
            produtoDAO.excluirProduto(id);
        } catch (PersistenceException e){
            throw new PersistenceException(e);
        }
    }
    public ProdutoDTO buscarProduto(Integer id){return produtoDAO.buscarProduto(id);}
    public ProdutoDTO buscarProdutoPorNome(String nome){return produtoDAO.buscarProdutoPorNome(nome);}
    public List<ProdutoDTO> listarProdutos(){return produtoDAO.listarProdutos();}
    public List<String> listarCategorias(){return produtoDAO.listarCategorias();}
    public List<String> listarFornecedores(){return produtoDAO.listarFornecedores();}

}
