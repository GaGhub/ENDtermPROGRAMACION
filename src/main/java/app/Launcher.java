package app;

// Importo la clase que se encarga de las ventanas
import gui.HelloApplication;
import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        /*
         He modificado el Launcher para que cargue HelloApplication. Esto ignorará la consola y arrancará el modo gráfico.
         Según mis apuntes de OOP, aquí usaré el Polimorfismo:
        El Launcher usa el métudo 'launch' de la clase Application para iniciar la clase específica 'HelloApplication'.
         */
        Application.launch(HelloApplication.class, args);
    }
}