package org.acgproject.gerencimentodeestoque.dao.impl;

import jakarta.persistence.EntityManager;
import org.acgproject.gerencimentodeestoque.db.DB;
import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.mapper.ProdutoMapper;
import org.acgproject.gerencimentodeestoque.model.entities.Produto;

import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {

    @Override
    public void inserirProduto(ProdutoDTO produtoDTO) {
        try(EntityManager em = DB.getConexao()) {
            Produto produto = ProdutoMapper.toEntity(produtoDTO);
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();
        }
    }

    @Override
    public void alterarProduto(ProdutoDTO produtoDTO) {
        try(EntityManager em = DB.getConexao()) {
            Produto produto = ProdutoMapper.toEntity(produtoDTO);
            em.getTransaction().begin();
            em.merge(produto);
            em.getTransaction().commit();
        }
    }

    @Override
    public void excluirProduto(Integer id) {
        try(EntityManager em = DB.getConexao()) {
            Produto produto = em.find(Produto.class, id);
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        }
    }

    @Override
    public ProdutoDTO buscarProduto(Integer id) {
        try(EntityManager em = DB.getConexao()) {
            Produto produto = em.find(Produto.class, id);
            return ProdutoMapper.toDTO(produto);
        }
    }

    @Override
    public List<ProdutoDTO> listarProdutos() {
        try(EntityManager em = DB.getConexao()) {
            List<Produto> produtos = em.createQuery("from Produto").getResultList();
            return ProdutoMapper.toDTOList(produtos);
        }
    }
}
