package org.acgproject.gerencimentodeestoque.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.mapper.ProdutoMapper;
import org.acgproject.gerencimentodeestoque.model.entities.Produto;

import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gerenciamentoestoque");

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void inserirProduto(ProdutoDTO produtoDTO) {
        try(EntityManager em = getEntityManager()) {
            Produto produto = ProdutoMapper.toEntity(produtoDTO);
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        }

    }

    @Override
    public void alterarProduto(ProdutoDTO produtoDTO) {
        try(EntityManager em = getEntityManager()) {
            Produto produto = ProdutoMapper.toEntity(produtoDTO);
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        }
    }

    @Override
    public void excluirProduto(Integer id) {
        try(EntityManager em = getEntityManager()) {
            Produto produto = em.find(Produto.class, id);
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        }
    }

    @Override
    public ProdutoDTO buscarProduto(Integer id) {
        try(EntityManager em = getEntityManager()) {
            Produto produto = em.find(Produto.class, id);
            return ProdutoMapper.toDTO(produto);
        }
    }

    @Override
    public List<ProdutoDTO> listarProdutos() {
        try(EntityManager em = getEntityManager()) {
            List<Produto> produtos = em.createQuery("from Produto").getResultList();
            return ProdutoMapper.toDTOList(produtos);
        }
    }
}
