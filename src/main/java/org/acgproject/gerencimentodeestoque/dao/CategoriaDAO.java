package org.acgproject.gerencimentodeestoque.dao;

import jakarta.persistence.PersistenceException;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public interface CategoriaDAO {

    void inserirCategoria(CategoriaDTO categoriaDTO);
    void alterarCategoria(CategoriaDTO categoriaDTO);
    void excluirCategoria(Integer id) throws PersistenceException;
    CategoriaDTO buscarCategoria(Integer id);
    CategoriaDTO buscarCategoriaPorNome(String nome);
    List<CategoriaDTO> listarCategorias();
    boolean nomeCategoriaExiste(String nome);


}
