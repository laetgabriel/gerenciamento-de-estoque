package org.acgproject.gerencimentodeestoque.utils;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.IOException;

public class RelatorioUtil {


    public static void adicionarTitulo(Document document, String titulo, PdfFont font) {
        document.add(new Paragraph(titulo)
                .setFont(font)
                .setFontSize(20)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));
    }

    public static Table criarTabela(float[] proporcoes, String[] cabecalhos, PdfFont font) {
        Table table = new Table(UnitValue.createPercentArray(proporcoes))
                .setWidth(UnitValue.createPercentValue(100));

        for (String cabecalho : cabecalhos) {
            table.addHeaderCell(new Cell().add(new Paragraph(cabecalho))
                    .setFont(font)
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY));
        }
        return table;
    }
}