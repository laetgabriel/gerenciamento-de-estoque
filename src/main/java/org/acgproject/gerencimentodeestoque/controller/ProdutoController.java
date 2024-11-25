package org.acgproject.gerencimentodeestoque.controller;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import jakarta.persistence.PersistenceException;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.acgproject.gerencimentodeestoque.dao.ProdutoDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.ProdutoDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;
import org.acgproject.gerencimentodeestoque.utils.FileChooserUtils;
import org.acgproject.gerencimentodeestoque.utils.RelatorioUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProdutoController {

    ProdutoDAO produtoDAO = new ProdutoDAOImpl();

    public void inserirProduto(ProdutoDTO produtoDTO){ produtoDAO.inserirProduto(produtoDTO);}
    public void alterarProduto(ProdutoDTO produtoDTO){ produtoDAO.alterarProduto(produtoDTO);}
    public void excluirProduto(Integer id) {
        try {
            produtoDAO.excluirProduto(id);
        } catch (PersistenceException e){
            throw new PersistenceException(e);
        }
    }
    public ProdutoDTO buscarProduto(Integer id){return produtoDAO.buscarProduto(id);}
    public ProdutoDTO buscarProdutoPorNome(String nome){return produtoDAO.buscarProdutoPorNome(nome);}
    public List<ProdutoDTO> listarProdutos(){return produtoDAO.listarProdutos();}
    public List<String> listarCategorias(){return produtoDAO.listarCategorias();}
    public List<String> listarFornecedores(){return produtoDAO.listarFornecedores();}
    public void gerarRelatorio(Stage stage, ObservableList<ProdutoDTO> produtos){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String dataAtual = LocalDateTime.now().format(formatter);

        FileChooserUtils fileChooserUtil = new FileChooserUtils();
        File file = fileChooserUtil.gerarFileChooser("Relatório_De_Produtos_" + dataAtual).showSaveDialog(stage);

        if (file != null) {
            try (PdfWriter writer = new PdfWriter(file);
                 PdfDocument pdfDoc = new PdfDocument(writer);
                 Document document = new Document(pdfDoc)) {

                PdfFont fontHeader = PdfFontFactory.createRegisteredFont("Helvetica-Bold");
                PdfFont fontBody = PdfFontFactory.createRegisteredFont("Helvetica");
                RelatorioUtil.adicionarTitulo(document, "Relatório de Produtos", fontHeader);

                Table table = RelatorioUtil.criarTabela(
                        new float[]{1, 2, 1, 1, 2, 2},
                        new String[]{"ID", "Nome", "Preço", "Quantidade", "Fornecedor", "Categoria"},
                        fontBody
                );

                for (ProdutoDTO produto: produtos) {
                    if (produto != null) {
                        table.addCell(new Paragraph(produto.getId().toString()));
                        table.addCell(new Paragraph(produto.getNome()));
                        table.addCell(new Paragraph(produto.getPreco().toString()));
                        table.addCell(new Paragraph(produto.getQuantidade().toString()));
                        table.addCell(new Paragraph(produto.getFornecedor().getNome()));
                        table.addCell(new Paragraph(produto.getCategoria().getNome()));
                    }
                }

                document.add(table);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao gerar relatório de Movimentações", e);
            }
        }
    }

}
