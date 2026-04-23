package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 Esta clase recupera la lógica que tenía en el Midterm. Es el "cerebro" que sabe sumar importes y gestionar la lista.
 */
public class GestorFinanzas {

    // Lista donde guardaré todos los Gastos e Ingresos. Ahora los tengo agrupados en la misma lista.
    private List<Operacion> listaOperaciones;

    // Constructor que inicializa la lista vacía
    public GestorFinanzas() {
        this.listaOperaciones = new ArrayList<>();
    }

    /*
     FUNCIÓN: Listar (Tarea #1 del EndTerm)
     Añado una operación y ordeno la lista automáticamente por fecha.
     */
    public void añadirOperacion(Operacion op) {
        listaOperaciones.add(op);
        // Gracias a que Operacion es "implements Comparable", puedo ordenar así:
        Collections.sort(listaOperaciones);
    }

    /*
   FUNCIÓN: Consultar Saldo (Lógica recuperada del Midterm)
   Recorre la lista y suma ingresos o resta gastos.
     */
    public double calcularSaldoActual() {
        double saldo = 0;
        for (Operacion op : listaOperaciones) {
            // Si la operación es un Ingreso, sumamos
            if (op instanceof Ingreso) {
                saldo += op.getImporte();
            }
            // Si es un Gasto, restamos
            else if (op instanceof Gasto) {
                saldo -= op.getImporte();
            }
        }
        return saldo;
    }

    /*
   FUNCIÓN: Obtener lista
   Devuelve la lista para que la Tabla de la interfaz (GUI) pueda mostrarla.
     */
    public List<Operacion> getListaOperaciones() {
        return listaOperaciones;
    }
}