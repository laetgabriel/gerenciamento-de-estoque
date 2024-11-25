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
import org.acgproject.gerencimentodeestoque.dao.FornecedorDAO;
import org.acgproject.gerencimentodeestoque.dao.impl.FornecedorDAOImpl;
import org.acgproject.gerencimentodeestoque.dto.FornecedorDTO;
import org.acgproject.gerencimentodeestoque.utils.FileChooserUtils;
import org.acgproject.gerencimentodeestoque.utils.RelatorioUtil;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FornecedorController {

    private FornecedorDAO fornecedorDAO = new FornecedorDAOImpl();

    public void inserirFornecedor(FornecedorDTO fornecedor) throws PersistenceException {
        try {
            fornecedorDAO.inserirFornecedor(fornecedor) ;
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }

    public void atualizarFornecedor(FornecedorDTO fornecedor) throws PersistenceException {
        try {
            fornecedorDAO.alterarFornecedor(fornecedor);
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }
    public void excluirFornecedor(int id) throws PersistenceException {
        try {
            fornecedorDAO.excluirFornecedor(id);
        } catch (PersistenceException e) {
            throw new PersistenceException(e);
        }
    }
    public FornecedorDTO consultarFornecedorPorNome(String nome){return fornecedorDAO.consultarFornecedorPorNome(nome);}

    public List<FornecedorDTO> listarTodosOsFornecedores() { return fornecedorDAO.listarTodosOsFornecedores();}

    public void gerarRelatorio(Stage stage, ObservableList<FornecedorDTO> fornecedores) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String dataAtual = LocalDateTime.now().format(formatter);

        FileChooserUtils fileChooserUtil = new FileChooserUtils();
        File file = fileChooserUtil.gerarFileChooser("Relatório_Fornecedor_" + dataAtual).showSaveDialog(stage);

        if (file != null) {
            try (PdfWriter writer = new PdfWriter(file);
                 PdfDocument pdfDoc = new PdfDocument(writer);
                 Document document = new Document(pdfDoc)) {

                PdfFont fontHeader = PdfFontFactory.createRegisteredFont("Helvetica-Bold");
                PdfFont fontBody = PdfFontFactory.createRegisteredFont("Helvetica");
                RelatorioUtil.adicionarTitulo(document, "Relatório de Fornecedores", fontHeader);

                Table table = RelatorioUtil.criarTabela(
                        new float[]{1, 3, 3, 3},
                        new String[]{"ID", "Nome", "Telefone", "Email"},
                        fontBody);

                // Preencher a tabela com os dados dos fornecedores
                for (FornecedorDTO fornecedor : fornecedores) {
                    if (fornecedor != null) {
                        table.addCell(new Paragraph(fornecedor.getId().toString()));
                        table.addCell(new Paragraph(fornecedor.getNome()));
                        table.addCell(new Paragraph(fornecedor.getTelefone()));
                        table.addCell(new Paragraph(fornecedor.getEmail()));
                    }
                }

                document.add(table);
            } catch (IOException e) {
                throw new RuntimeException("Erro ao gerar relatório de fornecedores", e);
            }
        }
    }
}
