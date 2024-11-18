module com.rova.jasypt {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.guice;
    requires jasypt;

    opens com.rova.jasypt.controllers to javafx.fxml, com.google.guice;
    opens com.rova.jasypt.services to jasypt, com.google.guice;
    
    exports com.rova.jasypt;
}
