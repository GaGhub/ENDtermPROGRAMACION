package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        /*
         CARGA DEL DISEÑO
         Cambio la ruta para que busque "VistaOperaciones.fxml" dentro de la carpeta "gui" de resources
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/gui/VistaOperaciones.fxml"));

        // Creo la escena con el contenido del archivo FXML
        Scene scene = new Scene(fxmlLoader.load());

        // Le añado el "Título" que aparecerá en la parte superior de la ventana
        stage.setTitle("Gestión de Finanzas Personales");

        // Coloco la escena en la ventana y la muestro
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
