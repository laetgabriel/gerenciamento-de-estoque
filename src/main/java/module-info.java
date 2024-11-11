module org.acgproject.gerencimentodeestoque {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.acgproject.gerencimentodeestoque to javafx.fxml;
    exports org.acgproject.gerencimentodeestoque.view;
    opens org.acgproject.gerencimentodeestoque.view to javafx.fxml;
    exports org.acgproject.gerencimentodeestoque.view.controller;
    opens org.acgproject.gerencimentodeestoque.view.controller to javafx.fxml;
}