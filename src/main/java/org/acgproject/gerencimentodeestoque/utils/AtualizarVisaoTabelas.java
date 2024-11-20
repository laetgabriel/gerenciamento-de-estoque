package org.acgproject.gerencimentodeestoque.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;

import java.util.List;

public class AtualizarVisaoTabelas {

    public static ObservableList<ProdutoDTO> tabelaFiltradaProduto(String filtroFornecedor, String filtroNomeProduto,
                                                                   String filtroCategoria,
                                                                   List<ProdutoDTO> produtos,
                                                                   TableView<ProdutoDTO> table) {

        ObservableList<ProdutoDTO> listaFiltrada = FXCollections.observableArrayList();

        for (ProdutoDTO produto : produtos) {
            if ((filtroFornecedor == null || filtroFornecedor.isEmpty() || filtroPorFornecedor(produto, filtroFornecedor)) &&
                    (filtroNomeProduto == null || filtroNomeProduto.isEmpty() || filtroNomeProduto(produto, filtroNomeProduto)) &&
                    (filtroCategoria == null || filtroCategoria.isEmpty() || filtroPorCategoria(produto, filtroCategoria))) {
                listaFiltrada.add(produto);
            }

        }
        table.setItems(listaFiltrada);
        return listaFiltrada;
    }

    public static boolean filtroPorFornecedor(ProdutoDTO produtoDTO, String filtroFornecedor) {
        if (produtoDTO.getFornecedor().getNome().equalsIgnoreCase(filtroFornecedor) || filtroFornecedor.equalsIgnoreCase("Sem fornecedor")) {
            return true;
        }

        return false;
    }

    public static boolean filtroNomeProduto(ProdutoDTO produtoDTO, String filtroNomeProduto) {
        if(produtoDTO.getNome().toLowerCase().contains(filtroNomeProduto.toLowerCase()))
            return true;

        return false;
    }

    public static boolean filtroPorCategoria(ProdutoDTO produtoDTO, String filtroCategoria) {
        if(produtoDTO.getCategoria().getNome().equalsIgnoreCase(filtroCategoria) || filtroCategoria.equalsIgnoreCase("Sem categoria"))
            return true;

        return false;
    }
}
