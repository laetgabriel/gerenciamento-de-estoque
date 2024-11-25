package org.acgproject.gerencimentodeestoque.controller;

import jakarta.persistence.PersistenceException;
import org.acgproject.gerencimentodeestoque.dao.MovimentacaoEstoqueDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.MovimentacaoEstoqueDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;

import java.time.LocalDate;
import java.util.List;

public class MovimentacaoEstoqueController {

    private MovimentacaoEstoqueDAO movimentacaoEstoqueDAO = new MovimentacaoEstoqueDAOImpl();

    public void inserirMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {movimentacaoEstoqueDAO.inserirMovimentacaoEstoque(movimentacaoEstoqueDTO);}
    public void alterarMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {movimentacaoEstoqueDAO.alterarMovimentacaoEstoque(movimentacaoEstoqueDTO);}
    public void excluirMovimentacaoEstoque(Integer id) throws PersistenceException {movimentacaoEstoqueDAO.excluirMovimentacaoEstoque(id);}
    public MovimentacaoEstoqueDTO buscarMovimentacaoEstoque(Integer id) {return movimentacaoEstoqueDAO.buscarMovimentacaoEstoque(id);}
    public MovimentacaoEstoqueDTO buscarMovimentacaoEstoquePorProdutoCadastrado(ProdutoDTO produtoDTO, LocalDate data, Integer quantidade){
        return movimentacaoEstoqueDAO.buscarMovimentacaoEstoquePorProdutoCadastrado(produtoDTO, data, quantidade);}
    public List<MovimentacaoEstoqueDTO> listarMovimentacaoEstoque(){return movimentacaoEstoqueDAO.listarMovimentacaoEstoque();}

}