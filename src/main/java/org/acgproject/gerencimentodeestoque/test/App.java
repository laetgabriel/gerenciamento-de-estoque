package org.acgproject.gerencimentodeestoque.test;

import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.CategoriaDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;

import java.util.List;


public class App {

    public static void main(String[] args) {
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        List<CategoriaDTO> categoriaDTO = categoriaDAO.listarCategorias();
        categoriaDTO.forEach(System.out::println);
    }
}
