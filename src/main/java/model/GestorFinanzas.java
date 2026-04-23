package model;

import java.util.Set;      // Añado Set
import java.util.TreeSet;   // Importo la implementación TreeSet
import java.util.ArrayList;
import java.util.List;

/*
 GESTOR DE FINANZAS (TreeSet)
 Cumple con el requisito de almacenamiento ordenado automático.
 */
public class GestorFinanzas {

    /*
  Tarea #1: Usar TreeSet en lugar de ArrayList.
 El TreeSet mantendrá las operaciones ordenadas por fecha automáticamente gracias al compareTo() de la clase Operacion.
     */
    private Set<Operacion> listaOperaciones;

    public GestorFinanzas() {
        // Inicializo como TreeSet
        this.listaOperaciones = new TreeSet<>();
    }

    /*
 FUNCIÓN: Añadir Operación
Al usar TreeSet, la colección se encarga de colocar el objeto en la posición correcta según su fecha.
     */
    public void añadirOperacion(Operacion op) {
        listaOperaciones.add(op);
        // Ya no necesito Collections.sort() El TreeSet nunca se desordena.
    }

    /*
 FUNCIÓN: Obtener lista (para la tabla de la interfaz). Como la tabla de JavaFX (TableView) prefiere trabajar con Listas, convierto el Set a una Lista justo antes de enviarlo.
     */
    public List<Operacion> getListaOperaciones() {
        return new ArrayList<>(listaOperaciones);
    }

    public double calcularSaldoActual() {
        double saldo = 0;
        for (Operacion op : listaOperaciones) {
            if (op instanceof Ingreso) {
                saldo += op.getImporte();
            } else if (op instanceof Gasto) {
                saldo -= op.getImporte();
            }
        }
        return saldo;
    }
}

