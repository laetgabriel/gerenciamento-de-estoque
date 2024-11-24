package org.acgproject.gerencimentodeestoque.dao;

import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;

import java.util.List;

public interface MovimentacaoEstoqueDAO {
    void inserirMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO);
    void alterarMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO);
    void excluirMovimentacaoEstoque(Integer id);
    MovimentacaoEstoqueDTO buscarMovimentacaoEstoque(Integer id);
    List<MovimentacaoEstoqueDTO> listarMovimentacaoEstoque();
}
