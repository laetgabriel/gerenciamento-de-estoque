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
import org.acgproject.gerencimentodeestoque.dao.MovimentacaoEstoqueDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.MovimentacaoEstoqueDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.MovimentacaoEstoqueDTO;
import org.acgproject.gerencimentodeestoque.utils.FileChooserUtils;
import org.acgproject.gerencimentodeestoque.utils.RelatorioUtil;
import org.acgproject.gerencimentodeestoque.dto.ProdutoDTO;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;

public class MovimentacaoEstoqueController {

    private MovimentacaoEstoqueDAO movimentacaoEstoqueDAO = new MovimentacaoEstoqueDAOImpl();

    public void inserirMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {movimentacaoEstoqueDAO.inserirMovimentacaoEstoque(movimentacaoEstoqueDTO);}
    public void alterarMovimentacaoEstoque(MovimentacaoEstoqueDTO movimentacaoEstoqueDTO) {movimentacaoEstoqueDAO.alterarMovimentacaoEstoque(movimentacaoEstoqueDTO);}
    public void excluirMovimentacaoEstoque(Integer id) throws PersistenceException {movimentacaoEstoqueDAO.excluirMovimentacaoEstoque(id);}
    public MovimentacaoEstoqueDTO buscarMovimentacaoEstoque(Integer id) {return movimentacaoEstoqueDAO.buscarMovimentacaoEstoque(id);}
    public MovimentacaoEstoqueDTO buscarMovimentacaoEstoquePorProdutoCadastrado(ProdutoDTO produtoDTO, LocalDate data, Integer quantidade){
        return movimentacaoEstoqueDAO.buscarMovimentacaoEstoquePorProdutoCadastrado(produtoDTO, data, quantidade);}
    public List<MovimentacaoEstoqueDTO> listarMovimentacaoEstoque(){return movimentacaoEstoqueDAO.listarMovimentacaoEstoque();}
    public void gerarRelatorio(Stage stage, ObservableList<MovimentacaoEstoqueDTO> movimentacoes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String dataAtual = LocalDateTime.now().format(formatter);

        FileChooserUtils fileChooserUtil = new FileChooserUtils();
        File file = fileChooserUtil.gerarFileChooser("Relatório_De_Movimentações_" + dataAtual).showSaveDialog(stage);

        if (file != null) {
            try (PdfWriter writer = new PdfWriter(file);
                 PdfDocument pdfDoc = new PdfDocument(writer);
                 Document document = new Document(pdfDoc)) {

                PdfFont fontHeader = PdfFontFactory.createRegisteredFont("Helvetica-Bold");
                PdfFont fontBody = PdfFontFactory.createRegisteredFont("Helvetica");

                RelatorioUtil.adicionarTitulo(document, "Relatório de Movimentação de Estoque", fontHeader);

                Table table = RelatorioUtil.criarTabela(
                        new float[]{1, 3, 3, 3, 2},
                        new String[]{"ID", "Tipo", "Produto", "Quantidade", "Data"},
                        fontBody
                );

                for (MovimentacaoEstoqueDTO movimentacao : movimentacoes) {
                    if (movimentacao != null) {
                        table.addCell(new Paragraph(movimentacao.getId().toString()));
                        table.addCell(new Paragraph(movimentacao.getTipoMovimentacao().toString()));
                        table.addCell(new Paragraph(movimentacao.getProdutoDTO().getNome()));
                        table.addCell(new Paragraph(movimentacao.getQuantidade().toString()));
                        table.addCell(new Paragraph(movimentacao.getData().toString()));
                    }
                }

                document.add(table);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao gerar relatório de Movimentações", e);
            }
        }
    }

}