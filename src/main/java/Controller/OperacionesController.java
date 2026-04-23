package Controller; // Indica que este archivo está en la carpeta de la interfaz gráfica

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

//importo lista
import java.util.List;

/*
CLASE CONTROLADOR: Su función es recibir las órdenes de la ventana y enviarlas al GestorFinanzas.
 */
public class OperacionesController {

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
    private TableColumn<Operacion, String> colTipo; // Columna tipo
    @FXML
    private TableColumn<Operacion, String> colOrigen; //Columna origen

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
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colOrigen.setCellValueFactory(new PropertyValueFactory<>("origen"));
        // Llamada limpia a la clase de utilidad que he creado para precargar datos.
        TestData.precargarDatos(this.gestor);
        // Una vez configuradas las columnas, se llenará la tabla con lo que tenga el gestor
        actualizarTabla();
    }

    /*
   ACTUALIZAR TABLA:
  Para que la tabla se refresque, JavaFX usa "ObservableList". Es una lista que "vigila" los cambios y los saca en pantalla al momento.
     */
    private void actualizarTabla() {
        // Llamo al gestor para obtener los datos (vienen como ArrayList)
        List<Operacion> listaNormal = gestor.getListaOperaciones();

        // Creo la ObservableList a partir del ArrayList que he creado en GestorFinanzas
        ObservableList<Operacion> datosObservables = FXCollections.observableArrayList(listaNormal);

        // Se la envío a la tabla
        tablaOperaciones.setItems(datosObservables);
    }
    /*
 ON NUEVA OPERACIÓN CLIC:
Este es el "Evento de Acción". En el FXML, el botón debe tener onAction="#onNuevaOperacionClick".
     */
    /*
EVENTO: Abrir formulario de Nueva Operación:Este métudo carga el archivo FXML de la pequeña ventana de registro.
     */
    @FXML
    protected void onNuevaOperacionClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/NuevaOperacion.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // CONECTO AMBOS CONTROLADORES

            //Obtengo el controlador de la ventana que se acaba de cargar
            NuevaOperacionController nuevoControlador = fxmlLoader.getController();

            // Le paso el gestor (el que tiene la lista) al nuevo controlado. Muy importante para que ambos compartan la misma información.
            // Si no hiciera esto, la ventana de "Nueva Operación" crearía una lista nueva y vacía, y nunca vería los cambios en la tabla principal.
            nuevoControlador.setGestor(this.gestor);


            Stage stage = new Stage();
            stage.setTitle("Añadir Nueva Operación");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait(); // Espero a que el usuario cierre la ventanita. Este métouo detiene la ejecución del HelloController justo ahí.
            // Cuando la ventana de "Nueva Operación" se cierre, el programa continúa y ejecuta actualizarTabla(), mostrando el nuevo gasto al instante.

            // Al volver, refresco la tabla con lo que se haya guardado
            actualizarTabla();

        } catch (IOException e) {
            System.err.println("ERROR: No se pudo abrir el formulario.");
            e.printStackTrace();
        }
    }
}