package model;

import dao.DaoOperacion;
import dao.DaoOperacionImplementacion;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Clase GestorFinanzas (Modelo)
 * Ahora actúa como puente entre el Controlador y la Capa de Datos (DAO).
 */
public class GestorFinanzas {

    // Tarea #3: Creamos una instancia de la implementación del DAO
    private DaoOperacion dao = new DaoOperacionImplementacion();

    /**
     * Tarea #3: Añade una operación guardándola permanentemente en la base de datos.
     * Ya no usamos una lista interna en RAM.
     */
    public void añadirOperacion(Operacion op) {
        // Delegamos la responsabilidad de guardar al DAO
        boolean exito = dao.insertar(op);

        if (!exito) {
            // Manejo de excepciones según el resumen S05
            System.err.println("ERROR: No se ha podido guardar la operación en la BD.");
        }
    }

    /**
     * Tarea #3: Recupera todas las operaciones directamente desde MySQL.
     * Esto asegura que los datos persistan aunque cerremos la aplicación.
     */
    public List<Operacion> getListaOperaciones() {
        // El DAO nos devuelve un TreeSet (manteniendo el orden de la Tarea #1)
        Set<Operacion> datosDeBD = dao.listarTodas();

        // Convertimos el Set a una List para que sea compatible con la TableView de JavaFX
        return new ArrayList<>(datosDeBD);
    }

}