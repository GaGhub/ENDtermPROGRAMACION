package model;

import dao.DaoOperacion;
import dao.DaoOperacionImplementacion;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
Clase GestorFinanzas (Modelo): Ahora actúa como puente entre el Controlador y la Capa de Datos (DAO).
 */
public class GestorFinanzas {

// Creamos una instancia de la implementación del DAO
    private DaoOperacion dao = new DaoOperacionImplementacion();
    /*
 Añade una operación guardándola permanentemente en la base de datos. Ya no usaré una lista interna en RAM.
     */
    public void añadirOperacion(Operacion op) {
        // Delegamos la responsabilidad de guardar al DAO
        boolean exito = dao.insertar(op);

        if (!exito) {
            // Manejo de excepciones
            System.err.println("ERROR: No se ha podido guardar la operación en la BD.");
        }
    }

    /*
 Recupera todas las operaciones directamente desde MySQL. Esto asegura que los datos persistan aunque cierre la aplicación.
     */
    public List<Operacion> getListaOperaciones() {
        // El DAO nos devuelve un TreeSet (manteniendo el orden)
        Set<Operacion> datosDeBD = dao.listarTodas();

        // Convierto el Set a una List para que sea compatible con la TableView de JavaFX
        return new ArrayList<>(datosDeBD);
    }

}