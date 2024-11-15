package org.acgproject.gerencimentodeestoque.controller;

import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.CategoriaDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;

import java.util.List;

public class CategoriaController {

    CategoriaDAO categoriaDAO = new CategoriaDAOImpl();


    public void inserirCategoria(CategoriaDTO categoriaDTO){categoriaDAO.inserirCategoria(categoriaDTO);}

    public void alterarCategoria(CategoriaDTO categoriaDTO){categoriaDAO.alterarCategoria(categoriaDTO);}

    public void excluirCategoria(Integer id){categoriaDAO.excluirCategoria(id);}

    public CategoriaDTO buscarCategoria(Integer id){return categoriaDAO.buscarCategoria(id);}

    public CategoriaDTO buscarCategoriaPorNome(String  nome) {return categoriaDAO.buscarCategoriaPorNome(nome);}

    public boolean nomeCategoriaExiste(String nome){return categoriaDAO.nomeCategoriaExiste(nome);}

    public List<CategoriaDTO> listarCategorias() { return categoriaDAO.listarCategorias(); }

}
