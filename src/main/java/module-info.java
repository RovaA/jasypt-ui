module com.rova.jasypt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.guice;
    requires jasypt;

    opens com.rova.jasypt to javafx.fxml, com.google.guice;
    // opens org.jasypt.encryption.pbe;
    
    exports com.rova.jasypt;
    exports com.rova.jasypt.services to com.google.guice;
}
