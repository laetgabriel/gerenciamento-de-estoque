module org.acgproject.gerencimentodeestoque {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.acgproject.gerencimentodeestoque to javafx.fxml;
    exports org.acgproject.gerencimentodeestoque;
}