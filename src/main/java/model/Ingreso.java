package model;

// Importamos LocalDate para la gestión de fechas
import java.time.LocalDate;

/*
 CLASE INGRESO (Clase Hija / Subclase)
 "Inheritance introduction": Usa 'extends Operacion' para heredar de la superclase.
 */
public class Ingreso extends Operacion {

    // Atributo específico: de dónde viene el dinero
    private String origen;

    /*
  CONSTRUCTOR 1: COMPLETO (Con descripción)
     */
    public Ingreso(String descripcion, double importe, LocalDate fecha, String origen) {
        /*
    Según tus apuntes "Inheritance Super keyword": Envia los datos comunes (descripción, importe, fecha) al padre.
         */
        super(descripcion, importe, fecha);

        // Guardo el dato que es solo de los ingresos
        this.origen = origen;
    }

    /*


CONSTRUCTOR 2: SIN DESCRIPCIÓN (Sobrecarga)
Corrección de la profe: Igual que he explicado en Gasto que en Gasto para permitir crear el objeto sin detalles extra.
     */
    public Ingreso(double importe, LocalDate fecha, String origen) {
        /*
    'this' con paréntesis llama al constructor de arriba. Ponemos 'null' porque no tenemos descripción.
         */
        this(null, importe, fecha, origen);
    }

    /*
     * MÉTUDO GETTER
Permite que la tabla de la interfaz gráfica lea el origen del ingreso.
     */
    public String getOrigen() {
        return origen;
    }
}