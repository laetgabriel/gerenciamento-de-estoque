package org.acgproject.gerencimentodeestoque.utils;

import javafx.stage.FileChooser;

public class FileChooserUtils {

    public static FileChooser gerarFileChooser(String nomeDoArquivo) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(nomeDoArquivo);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
    }
}

