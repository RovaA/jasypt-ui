package com.rova.jasypt;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.rova.jasypt.guice.GuiceMainModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.Callback;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"), 330, 380);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        
        Injector injector = Guice.createInjector(new GuiceMainModule());
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setControllerFactory(new ControllerFactory(injector));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static class ControllerFactory implements Callback<Class<?>, Object> {
        
        private final Injector injector;

        private ControllerFactory(Injector injector) {
            this.injector = injector;
        }

        @Override
        public Object call(Class<?> p) {
            return injector.getInstance(p);
        }
    }

}