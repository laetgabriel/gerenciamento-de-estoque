package org.acgproject.gerencimentodeestoque.dao;

import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaDAO {

    void inserirCategoria(CategoriaDTO categoriaDTO);
    void alterarCategoria(CategoriaDTO categoriaDTO);
    void excluirCategoria(Integer id);
    CategoriaDTO buscarCategoria(Integer id);
    List<CategoriaDTO> listarCategorias();
    boolean nomeCategoriaExiste(String nome);


}
