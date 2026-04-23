package gui; // Indica que este archivo está en la carpeta de la interfaz gráfica

// HERRAMIENTAS DE JAVA (Entrada/Salida)
import java.io.IOException; // Necesario para gestionar errores al leer archivos .fxml
// HERRAMIENTAS DE JAVAFX (Interfaz Gráfica)
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; // La herramienta que "lee" los archivos de diseño
import javafx.scene.Scene;      // Representa el contenido dentro de una ventana
import javafx.stage.Modality;   // Define si una ventana bloquea a la otra
import javafx.stage.Stage;      // Representa la ventana física (el "Escenario")

// CONTROLES DE LA VENTANA
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


// Importo el "motor" (Gasto, Ingreso, Gestor) para poder usarlos aquí
import model.*;

import java.time.LocalDate;

/*
CLASE CONTROLADOR: Su función es recibir las órdenes de la ventana y enviarlas al GestorFinanzas.
 */
public class HelloController {

    /*
  ETIQUETAS @FXML: Según mis apuntes de "Graphical User Interface", estas etiquetas conectan el código Java con los objetos del archivo .fxml.
  El nombre después de 'private' debe ser IGUAL al fx:id que puse en Scene Builder.
     */
    @FXML
    private TableView<Operacion> tablaOperaciones; // La tabla completa
    @FXML
    private TableColumn<Operacion, String> colDescripcion; // Columna descripción
    @FXML
    private TableColumn<Operacion, Double> colImporte;     // Columna importe
    @FXML
    private TableColumn<Operacion, LocalDate> colFecha;   // Columna fecha

    @FXML
    private Button btnNuevaOperacion; // El botón para añadir

    /*
  EL GESTOR (El cerebro):
 Aquí creo una instancia de la clase que tiene toda la lógica del Midterm (sumar saldo, lista de operaciones, etc.).
     */
    private GestorFinanzas gestor = new GestorFinanzas();

    /*
  MÉTUDO INITIALIZE:
  Este métudo es especial. No lo llamas yo específicamente, lo llama JavaFX automáticamente en cuanto la ventana se termina de cargar.
     */
    @FXML
    public void initialize() {
        /*
    PropertyValueFactory: Es una herramienta que le dice a la columna: "Busca en el objeto Operacion el métudo que se llame getDescripcion() y pon ese valor aquí".
         */
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colImporte.setCellValueFactory(new PropertyValueFactory<>("importe"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        // Una vez configuradas las columnas, se llenará la tabla con lo que tenga el gestor
        actualizarTabla();
    }

    /*
   ACTUALIZAR TABLA:
  Para que la tabla se refresque, JavaFX usa "ObservableList". Es una lista que "vigila" los cambios y los saca en pantalla al momento.
     */
    private void actualizarTabla() {
        // Convierto la lista normal del Gestor en una lista "observable" para la GUI
        ObservableList<Operacion> datosParaMostrar = FXCollections.observableArrayList(gestor.getListaOperaciones());

        // Le paso los datos a la tabla
        tablaOperaciones.setItems(datosParaMostrar);
    }

    /*
 ON NUEVA OPERACIÓN CLIC:
ste es el "Evento de Acción". En el FXML, el botón debe tener onAction="#onNuevaOperacionClick".
     */
    /*
EVENTO: Abrir formulario de Nueva Operación :Este métudo carga el archivo FXML de la pequeña ventana de registro.
     */
    @FXML
    protected void onNuevaOperacionClick() {
        try {
            // Cargo el archivo de la segunda ventana
            // Importante: La ruta debe ser exacta a donde ponga el archivo
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/NuevaOperacion.fxml"));

            // Creo la "Escena" con el diseño cargado
            Scene scene = new Scene(fxmlLoader.load());

            // Creo un nuevo "Escenario" (una ventana nueva)
            Stage stage = new Stage();

            // Configuro la ventana (Título y si es modal)
            stage.setTitle("Añadir Nueva Operación");

            /*
         MODALITY (Concepto de Interfaz Gráfica): WINDOW_MODAL hace que esta ventana bloquee la de atrás hasta que se cierre.
         Esto asegura que el usuario se centre en rellenar el formulario.
             */
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);

            // Meto la escena en la ventana y la muestro:
            stage.setScene(scene);
            stage.showAndWait(); // showAndWait detiene el código aquí hasta que se cierre la ventana

            // Cuando se cierre, refresco la tabla por si se ha guardado algo
            actualizarTabla();

        } catch (IOException e) {
            // Si el archivo .fxml no existe o tiene un error de nombre, saltará aquí
            System.err.println("ERROR: No se ha podido cargar la ventana de Nueva Operación.");
            e.printStackTrace();
        }
    }
}