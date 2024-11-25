package org.acgproject.gerencimentodeestoque.dao;

import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoEstoqueDAO {
    void inserirMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO);
    void alterarMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO);
    void excluirMovimentacaoEstoque(Integer id);
    MovimentacaoEstoqueDTO buscarMovimentacaoEstoque(Integer id);
    MovimentacaoEstoqueDTO buscarMovimentacaoEstoquePorProdutoCadastrado(ProdutoDTO produtoDTO, LocalDate data,
                                                                         Integer quantidade);
    List<MovimentacaoEstoqueDTO> listarMovimentacaoEstoque();
}
