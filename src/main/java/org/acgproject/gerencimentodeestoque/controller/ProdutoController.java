package org.acgproject.gerencimentodeestoque.controller;

import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.ProdutoDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;

import java.util.List;

public class ProdutoController {

    ProdutoDAO produtoDAO = new ProdutoDAOImpl();

    public void inserirProduto(ProdutoDTO produtoDTO){

        produtoDAO.inserirProduto(produtoDTO);

    }
    public void alterarProduto(ProdutoDTO produtoDTO){ produtoDAO.alterarProduto(produtoDTO);}
    public void excluirProduto(Integer id) { produtoDAO.excluirProduto(id);}
    public ProdutoDTO buscarProduto(Integer id){return produtoDAO.buscarProduto(id);}
    public List<ProdutoDTO> listarProdutos(){return produtoDAO.listarProdutos();}
}
