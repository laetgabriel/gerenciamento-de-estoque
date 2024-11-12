module org.acgproject.gerencimentodeestoque {
    requires javafx.controls;
    requires javafx.fxml;

    // Abre o pacote 'view' para reflexão, necessário para carregar FXML
    opens org.acgproject.gerencimentodeestoque.view to javafx.fxml;
    // Exporta o pacote 'view' para que outras partes do projeto possam acessá-lo
    exports org.acgproject.gerencimentodeestoque.view;

    // Exporta e abre o pacote 'controller' para o JavaFX acessar os controladores
    exports org.acgproject.gerencimentodeestoque.controller;
    opens org.acgproject.gerencimentodeestoque.controller to javafx.fxml;

}
