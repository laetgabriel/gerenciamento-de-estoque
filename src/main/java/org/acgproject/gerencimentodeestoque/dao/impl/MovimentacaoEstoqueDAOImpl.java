package org.acgproject.gerencimentodeestoque.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.acgproject.gerencimentodeestoque.dao.MovimentacaoEstoqueDAO;
import org.acgproject.gerencimentodeestoque.db.DB;
import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.mapper.MovimentacaoEstoqueMapper;
import org.acgproject.gerencimentodeestoque.model.entities.MovimentacaoEstoque;

import java.util.List;

public class MovimentacaoEstoqueDAOImpl implements MovimentacaoEstoqueDAO {
    @Override
    public void inserirMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {
        try (EntityManager entityManager = DB.getConexao()) {
            MovimentacaoEstoque movimentacaoEstoque = MovimentacaoEstoqueMapper.toEntity(movimentacaoEstoqueDTO);
            entityManager.getTransaction().begin();
            entityManager.persist(movimentacaoEstoque);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void alterarMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {
        try (EntityManager entityManager = DB.getConexao()) {
            MovimentacaoEstoque movimentacaoEstoque = MovimentacaoEstoqueMapper.toEntity(movimentacaoEstoqueDTO);
            entityManager.getTransaction().begin();
            entityManager.merge(movimentacaoEstoque);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void excluirMovimentacaoEstoque(Integer id) {
        try (EntityManager entityManager = DB.getConexao()) {
            entityManager.getTransaction().begin();
            MovimentacaoEstoque movimentacaoEstoque = entityManager.find(MovimentacaoEstoque.class, id);
            if (movimentacaoEstoque != null) {
                entityManager.remove(movimentacaoEstoque);
            }
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public MovimentacaoEstoqueDTO buscarMovimentacaoEstoque(Integer id) {
        try (EntityManager entityManager = DB.getConexao()) {
            MovimentacaoEstoque movimentacaoEstoque = entityManager.find(MovimentacaoEstoque.class, id);
            return MovimentacaoEstoqueMapper.toDTO(movimentacaoEstoque);
        }
    }

    @Override
    public List<MovimentacaoEstoqueDTO> listarMovimentacaoEstoque() {
        try (EntityManager entityManager = DB.getConexao()) {
            List<MovimentacaoEstoque> movimentacoes = entityManager.createQuery("from MovimentacaoEstoque ", MovimentacaoEstoque.class).getResultList();
            return MovimentacaoEstoqueMapper.toDTOList(movimentacoes);
        }
    }
}
