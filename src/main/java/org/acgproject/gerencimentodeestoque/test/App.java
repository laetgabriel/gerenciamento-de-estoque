package org.acgproject.gerencimentodeestoque.test;

import org.acgproject.gerencimentodeestoque.dao.CategoriaDAO;
import org.acgproject.gerencimentodeestoque.dao.FornecedorDAO;
import org.acgproject.gerencimentodeestoque.dao.MovimentacaoEstoqueDAO;
import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.CategoriaDAOImpl;
import org.acgproject.gerencimentodeestoque.dao.impl.FornecedorDAOImpl;
import org.acgproject.gerencimentodeestoque.dao.impl.MovimentacaoEstoqueDAOImpl;
import org.acgproject.gerencimentodeestoque.dao.impl.ProdutoDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.CategoriaDTO;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.model.entities.Categoria;
import org.acgproject.gerencimentodeestoque.model.entities.Fornecedor;
import org.acgproject.gerencimentodeestoque.model.entities.MovimentacaoEstoque;
import org.acgproject.gerencimentodeestoque.model.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class App {

    public static void main(String[] args) {
        MovimentacaoEstoqueDAO movimentacaoEstoqueDAO = new MovimentacaoEstoqueDAOImpl();
        ProdutoDAOImpl produtoDAO = new ProdutoDAOImpl();


        System.out.println(movimentacaoEstoqueDAO.buscarMovimentacaoEstoque(13));


    }
}