package org.acgproject.gerencimentodeestoque.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.acgproject.gerencimentodeestoque.db.DB;
import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.mapper.CategoriaMapper;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;

import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public void inserirCategoria(CategoriaDTO categoriaDTO) {
        try (EntityManager entityManager = DB.getConexao()) {
            Categoria categoria = CategoriaMapper.toEntity(categoriaDTO);
            entityManager.getTransaction().begin();
            entityManager.persist(categoria);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void alterarCategoria(CategoriaDTO categoriaDTO) {
        try (EntityManager entityManager = DB.getConexao()) {
            Categoria categoria = CategoriaMapper.toEntity(categoriaDTO);
            entityManager.getTransaction().begin();
            entityManager.merge(categoria);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void excluirCategoria(Integer id) {
        try (EntityManager entityManager = DB.getConexao()) {
            entityManager.getTransaction().begin();
            Categoria categoria = entityManager.find(Categoria.class, id);
            if (categoria != null) {
                entityManager.remove(categoria);
            }
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public CategoriaDTO buscarCategoria(Integer id) {
        try (EntityManager entityManager = DB.getConexao()) {
            Categoria categoria = entityManager.find(Categoria.class, id);
            return CategoriaMapper.toDTO(categoria);
        }
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        try (EntityManager entityManager = DB.getConexao()) {
            List<Categoria> categorias = entityManager.createQuery("from Categoria", Categoria.class).getResultList();
            return CategoriaMapper.toDTOList(categorias);
        }
    }

    @Override
    public boolean nomeCategoriaExiste(String nome) {
        try (EntityManager entityManager = DB.getConexao()) {
            TypedQuery<Categoria> query = entityManager.createQuery("from Categoria where nome = :nome", Categoria.class);
            query.setParameter("nome", nome);

            return !query.getResultList().isEmpty();
        }
    }
}
