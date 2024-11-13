package org.acgproject.gerencimentodeestoque.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.mapper.CategoriaMapper;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;

import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gerenciamentoestoque");

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void inserirCategoria(CategoriaDTO categoriaDTO) {
        try (EntityManager entityManager = getEntityManager()) {
            Categoria categoria = CategoriaMapper.toEntity(categoriaDTO);
            entityManager.getTransaction().begin();
            entityManager.persist(categoria);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void alterarCategoria(CategoriaDTO categoriaDTO) {
        try (EntityManager entityManager = getEntityManager()) {
            Categoria categoria = CategoriaMapper.toEntity(categoriaDTO);
            entityManager.getTransaction().begin();
            entityManager.merge(categoria);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public void excluirCategoria(Integer id) {
        try (EntityManager entityManager = getEntityManager()) {
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
        try (EntityManager entityManager = getEntityManager()) {
            Categoria categoria = entityManager.find(Categoria.class, id);
            return CategoriaMapper.toDTO(categoria);
        }
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        try (EntityManager entityManager = getEntityManager()) {
            List<Categoria> categorias = entityManager.createQuery("from Categoria", Categoria.class).getResultList();
            return CategoriaMapper.toDTOList(categorias);
        }
    }

    public void closeFactory() {
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
