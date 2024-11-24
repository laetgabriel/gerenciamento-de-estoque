package org.acgproject.gerencimentodeestoque.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import java.time.LocalDate;
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

    public static ObservableList<FornecedorDTO> tabelaFiltradaFornecedor(String filtroFornecedor,
                                                                   List<FornecedorDTO> fornecedores,
                                                                   TableView<FornecedorDTO> table) {

        ObservableList<FornecedorDTO> listaFiltrada = FXCollections.observableArrayList();

        for (FornecedorDTO fornecedorDTO : fornecedores) {
            if (filtroFornecedor == null || filtroFornecedor.isEmpty() || filtroNomeFornecedor(fornecedorDTO, filtroFornecedor))
                listaFiltrada.add(fornecedorDTO);


        }
        table.setItems(listaFiltrada);
        return listaFiltrada;
    }

    public static ObservableList<MovimentacaoEstoqueDTO> tabelaFiltradaMovimentacao(String filtroData, String filtroNomeProduto,
                                                                               String filtroTipo,
                                                                               List<MovimentacaoEstoqueDTO> movimentacoes,
                                                                               TableView<MovimentacaoEstoqueDTO> table) {

        ObservableList<MovimentacaoEstoqueDTO> listaFiltrada = FXCollections.observableArrayList();

        for (MovimentacaoEstoqueDTO movimentacao : movimentacoes) {
            if ((filtroData == null || filtroData.isEmpty() || filtroPorData(movimentacao, filtroData)) &&
                    (filtroNomeProduto == null || filtroNomeProduto.isEmpty() || filtroNomeProduto(movimentacao.getProdutoDTO(),
                            filtroNomeProduto)) &&
                    (filtroTipo == null || filtroTipo.isEmpty() || filtroPorTipo(movimentacao, filtroTipo))) {
                listaFiltrada.add(movimentacao);
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

    public static boolean filtroNomeFornecedor(FornecedorDTO fornecedorDTO, String filtroNomeFornecedor) {
        if(fornecedorDTO.getNome().toLowerCase().contains(filtroNomeFornecedor.toLowerCase()))
            return true;

        return false;
    }

    public static boolean filtroPorData(MovimentacaoEstoqueDTO movimentacaoDTO, String filtroData) {
        LocalDate hoje = LocalDate.now();

        switch (filtroData) {
            case "Hoje":
                return movimentacaoDTO.getData().isEqual(hoje);
            case "Semanal":
                return movimentacaoDTO.getData().isAfter(hoje.minusDays(7)) && movimentacaoDTO.getData().isBefore(hoje.plusDays(1));
            case "Mês":
                return movimentacaoDTO.getData().getMonth().equals(hoje.getMonth());
            case "Todos":
            default:
                return true;
        }
    }

    public static boolean filtroPorTipo(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO, String filtroTipo) {
        if(movimentacaoEstoqueDTO.getTipoMovimentacao().toString().equalsIgnoreCase(filtroTipo) || filtroTipo.equalsIgnoreCase(
                "Todos"))
            return true;
        return false;
    }

}
