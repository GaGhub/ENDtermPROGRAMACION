package Controller; // Define que este archivo pertenece al paquete de la interfaz gráfica

// IMPORTACIONES DE JAVAFX (Los controles que aparecen en el FXML)
import javafx.fxml.FXML; // Permite conectar variables Java con el archivo .fxml
import javafx.scene.control.*; // Importa botones, cajas de texto, desplegables, etc.
import javafx.stage.Stage; // Representa la ventana física para poder cerrarla

// IMPORTACIONES DEL MOTOR DE DATOS
import model.*; // Importa Gasto, Ingreso, Operacion, Gestor, Categoria y MetodoPago. En definitiva, lo que hay dentro de la carpeta
import java.time.LocalDate; // Herramienta para manejar la fecha

/*
 Creo la CLASE NuevaOperacionController: Se encarga de recoger lo que el usuario escribe y convertirlo en un objeto real.
 */
public class NuevaOperacionController {

    /*
  ETIQUETAS @FXML: Estas variables deben llamarse EXACTAMENTE igual que el 'fx:id' en Scene Builder.
     */
    @FXML private ComboBox<String> cmbTipo;    // Desplegable para elegir "Gasto" o "Ingreso"
    @FXML private TextField txtDescripcion;    // Caja de texto para el detalle
    @FXML private TextField txtImporte;        // Caja de texto para el dinero (es un String)
    @FXML private DatePicker dpFecha;          // Calendario para elegir el día
    @FXML private TextField txtOrigen;        // Caja para 'Origen' (Ingreso) o 'Categoría' (Gasto)

    /*
  EL GESTOR COMPARTIDO
 Si creara un "new GestorFinanzas" aquí habría conflicto, porque borraríamos los datos de la otra ventana.
 Usaré esta variable para recibir el gestor que ya tiene la lista principal.
     */
    private GestorFinanzas gestor;

    /*
 MÉTUDO initialize()
  Se ejecuta solo al abrirse esta ventanita. Prepara los valores por defecto.
     */
    @FXML
    public void initialize() {
        // Añado las dos opciones al desplegable
        cmbTipo.getItems().addAll("Gasto", "Ingreso");
        cmbTipo.setValue("Gasto"); // Marco "Gasto" por defecto para evitar errores
        dpFecha.setValue(LocalDate.now()); // Pongo la fecha de hoy automáticamente
    }

    /*
    MÉTUDO setGestor
  Es el "puente". HelloController llama a este métudo para pasarnos la lista de datos.
     */
    public void setGestor(GestorFinanzas gestor) {
        this.gestor = gestor; // Guarda la referencia del gestor principal
    }

    /*
  ACCIÓN DEL BOTÓN GUARDAR: Aquí es donde transformaré los textos en objetos del paquete 'model'.
     */
    @FXML
    private void onGuardarClick() {
        try {
            // EXTRAIGO DATOS DE LA GUI
            String tipo = cmbTipo.getValue(); // Obtiene "Gasto" o "Ingreso"
            String desc = txtDescripcion.getText(); // Obtiene el texto de descripción
            double importe = Double.parseDouble(txtImporte.getText()); // Convierte el texto en número decimal
            LocalDate fecha = dpFecha.getValue(); // Obtiene la fecha del calendario
            String textoUsuario = txtOrigen.getText().toUpperCase();/* Obtengo el origen o categoría. Leo el texto de la caja "Origen" y lo pasamos a MAYÚSCULAS
            porque los Enums suelen estar en mayúsculas (COMIDA, HOGAR...)*/

            // LE APLICO POLIMORFISMO (Concepto clave de los apuntes)
            // Creo una variable de la clase padre (Operacion)
            Operacion nuevaOp;

            //Creo las condiciones según la elección_
            if ("Gasto".equals(tipo)) {
                /*
                 Uso de Enums.
                * Intento convertir el texto del usuario en un Enum real.
             */
                Categoria catFinal;
                try {
                    // valueOf busca en el Enum Categoria algo que coincida con el texto
                    catFinal = Categoria.valueOf(textoUsuario);
                } catch (IllegalArgumentException e) {
                    // Si el usuario escribe "PATATAS" y eso no existe en el Enum,
                    // le ponemos "OTROS" por defecto para que el programa no explote.
                    catFinal = Categoria.OTROS;
                }

                // AHORA SÍ: Usamos la categoría que hemos encontrado, no una fija
                nuevaOp = new Gasto(desc, importe, fecha, catFinal, MetodoPago.EFECTIVO);

            } else {
                // En ingresos es más fácil porque el origen es un String (texto)
                nuevaOp = new Ingreso(desc, importe, fecha, textoUsuario);
            }

            gestor.añadirOperacion(nuevaOp);
            cerrarVentana();

        } catch (NumberFormatException e) {
            System.err.println("ERROR: El importe debe ser un número.");
        }
    }
    /*
   ACCIÓN DEL BOTÓN CANCELAR
     */
    @FXML
    private void onCancelarClick() {
        cerrarVentana(); // Simplemente cierra sin guardar nada
    }

    /*
  LÓGICA PARA CERRAR LA VENTANA
     */
    private void cerrarVentana() {
        // Busco la ventana (Stage) a través de cualquier control (ej: txtImporte)
        Stage stage = (Stage) txtImporte.getScene().getWindow();
        stage.close(); // Orden de cierre
    }
}
