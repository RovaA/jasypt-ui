module com.rova.jasypt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.guice;
    requires jasypt;

    opens com.rova.jasypt.controllers to javafx.fxml, com.google.guice;
    
    exports com.rova.jasypt;
    exports com.rova.jasypt.services to com.google.guice;
}
