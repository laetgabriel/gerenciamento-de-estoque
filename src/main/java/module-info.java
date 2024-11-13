module org.acgproject.gerencimentodeestoque {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens org.acgproject.gerencimentodeestoque.model.entities;
    opens org.acgproject.gerencimentodeestoque.view to javafx.fxml;
    exports org.acgproject.gerencimentodeestoque.view;

    exports org.acgproject.gerencimentodeestoque.controller;
    opens org.acgproject.gerencimentodeestoque.controller to javafx.fxml;

}
