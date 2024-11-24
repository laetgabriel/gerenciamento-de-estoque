package org.acgproject.gerencimentodeestoque.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.acgproject.gerencimentodeestoque.db.DB;
import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.mapper.ProdutoMapper;
import org.acgproject.gerencimentodeestoque.model.entities.Produto;

import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO{

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
    public void excluirProduto(Integer id) throws PersistenceException {
        try(EntityManager em = DB.getConexao()) {
            Produto produto = em.find(Produto.class, id);
            em.getTransaction().begin();
            em.remove(produto);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
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
    public ProdutoDTO buscarProdutoPorNome(String nome) {
        try (EntityManager entityManager = DB.getConexao()) {
            TypedQuery<Produto> query = entityManager.createQuery(
                    "from Produto where nome = :nome", Produto.class);
            query.setParameter("nome", nome);

            List<Produto> resultados = query.getResultList();

            if (resultados.isEmpty()) {
                return null;
            }

            return ProdutoMapper.toDTO(resultados.get(0));
        }
    }


    @Override
    public List<ProdutoDTO> listarProdutos() {
        try(EntityManager em = DB.getConexao()) {
            List<Produto> produtos = em.createQuery("from Produto").getResultList();
            return ProdutoMapper.toDTOList(produtos);
        }
    }

    public List<String> listarCategorias() {
        try (EntityManager em = DB.getConexao()){
            Query query = em.createNativeQuery(
                    "SELECT DISTINCT c.nome " +
                            "FROM gerenciamentoestoque.produto p " +
                            "INNER JOIN gerenciamentoestoque.categoria c ON p.id_categoria = c.id"
            );
            return query.getResultList();
        }
    }

    @Override
    public List<String> listarFornecedores() {
        try (EntityManager em = DB.getConexao()){
            Query query = em.createNativeQuery(
                    "SELECT DISTINCT f.nome " +
                            "FROM gerenciamentoestoque.produto p " +
                            "INNER JOIN gerenciamentoestoque.fornecedor f ON p.id_fornecedor = f.id"
            );
            return query.getResultList();
        }
    }

}
