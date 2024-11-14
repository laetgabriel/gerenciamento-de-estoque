package org.acgproject.gerencimentodeestoque.test;

import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.CategoriaDAOImpl;
import org.acgproject.gerencimentodeestoque.dao.impl.ProdutoDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;

import java.math.BigDecimal;
import java.util.List;


public class App {

    public static void main(String[] args) {
        CategoriaDAO categoriaDAO = new CategoriaDAOImpl();
        //categoriaDAO.inserirCategoria(new CategoriaDTO(null, "Categoria 1", null));
        //categoriaDAO.inserirCategoria(new CategoriaDTO(null, "Categoria 2", null));

        CategoriaDTO categoriaDTO = categoriaDAO.buscarCategoria(1);
        categoriaDTO.setDescricao("BLA BLA");
        categoriaDAO.alterarCategoria(categoriaDTO);

    }
}
