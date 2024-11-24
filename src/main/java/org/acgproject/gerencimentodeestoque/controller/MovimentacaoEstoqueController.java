package org.acgproject.gerencimentodeestoque.controller;

import jakarta.persistence.PersistenceException;
import org.acgproject.gerencimentodeestoque.dao.MovimentacaoEstoqueDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.MovimentacaoEstoqueDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;

import java.util.List;

public class MovimentacaoEstoqueController {

    private MovimentacaoEstoqueDAO movimentacaoEstoqueDAO = new MovimentacaoEstoqueDAOImpl();

    public void inserirMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {movimentacaoEstoqueDAO.inserirMovimentacaoEstoque(movimentacaoEstoqueDTO);}
    public void alterarMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {movimentacaoEstoqueDAO.alterarMovimentacaoEstoque(movimentacaoEstoqueDTO);}
    public void excluirMovimentacaoEstoque(Integer id) throws PersistenceException {movimentacaoEstoqueDAO.excluirMovimentacaoEstoque(id);}
    public MovimentacaoEstoqueDTO buscarMovimentacaoEstoque(Integer id) {return movimentacaoEstoqueDAO.buscarMovimentacaoEstoque(id);}
    public List<MovimentacaoEstoqueDTO> listarMovimentacaoEstoque(){return movimentacaoEstoqueDAO.listarMovimentacaoEstoque();}

}