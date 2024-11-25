module org.acgproject.gerencimentodeestoque {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires modelmapper;
    requires kernel;
    requires layout;
    requires io;

    opens org.acgproject.gerencimentodeestoque.model.entities;
    opens org.acgproject.gerencimentodeestoque.view to javafx.fxml;
    exports org.acgproject.gerencimentodeestoque.view;
    exports org.acgproject.gerencimentodeestoque.dto;
    exports org.acgproject.gerencimentodeestoque.controller;
    opens org.acgproject.gerencimentodeestoque.view.controller to javafx.fxml;
    opens org.acgproject.gerencimentodeestoque.controller to javafx.fxml;

}
