package org.acgproject.gerencimentodeestoque.db;

import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.CategoriaDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;

import java.util.List;


public class DB {

    public static void main(String[] args) {
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        List<CategoriaDTO> categoriaDTO = categoriaDAO.listarCategorias();
        categoriaDTO.forEach(System.out::println);
    }
}
