package org.acgproject.gerencimentodeestoque.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;

import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gerencimentoestoque");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Override
    public void inserirCategoria(CategoriaDTO categoriaDTO) {
        entityManager.getTransaction().begin();
        entityManager.persist(categoriaDTO);
        entityManager.getTransaction().commit();
    }

    @Override
    public void alterarCategoria(CategoriaDTO categoriaDTO) {
        entityManager.getTransaction().begin();
        entityManager.merge(categoriaDTO);
        entityManager.getTransaction().commit();
    }

    @Override
    public void excluirCategoria(Integer id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Categoria.class, id));
        entityManager.getTransaction().commit();
    }

    @Override
    public CategoriaDTO buscarCategoria(Integer id) {
        entityManager.getTransaction().begin();
        Categoria categoria = entityManager.find(Categoria.class, id);
        CategoriaDTO categoriaDTO =
        entityManager.getTransaction().commit();
        return categoria;
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {

    }
}
