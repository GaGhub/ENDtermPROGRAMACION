package model;

// Importo LocalDate para poder gestionar las fechas correctamente
import java.time.LocalDate;

/*
 * CLASE ABSTRACTA OPERACIÓN (Clase Padre / Superclase)
  "Inheritance Abstract classes":
 * Usa la palabra 'abstract' porque esta clase es una "categorización". Nunca crearemos una "Operación" a secas, sino un Gasto o un Ingreso.
  "Multiple inheritance Interfaces":
 * Añadimos 'implements Comparable' para obligar a esta clase a tener una regla de comparación, lo que nos permitirá ordenar la lista por fecha.
 */
public abstract class Operacion implements Comparable<Operacion> {

    /*
     ATRIBUTOS CON MODIFICADOR PROTECTED
   "Protected Access Modifier":
     En lugar de usar 'private', usamos 'protected'.
     Esto permite que las clases hijas (Gasto e Ingreso) puedan acceder a estos datos directamente, pero las clases de fuera del paquete no.
     */
    protected String descripcion; // Nombre o detalle del movimiento
    protected double importe;    // Cantidad de dinero
    protected LocalDate fecha;      // Día, mes y año del movimiento

    /*
      CONSTRUCTOR DE LA SUPERCLASE
     Este métudo se encarga de recibir los datos comunes y guardarlos en las "cajas" (variables) que hemos definido arriba.
     */
    public Operacion(String descripcion, double importe, LocalDate fecha) {
        this.descripcion = descripcion;
        this.importe = importe;
        this.fecha = fecha;
    }

    /*
     MÉTUDO DE COMPARACIÓN (MÉTUDO SOBRESCRITO)
    "Multiple inheritance Interfaces":
     Al implementar la interfaz Comparable, estay obligado a escribir el métudo 'compareTo'. Este métudo decide si una operación va antes o
     después que otra. En este caso, uso la fecha para decidir el orden.
     */
    @Override
    public int compareTo(Operacion otra) {
        // Usa el compareTo que ya viene dentro de las fechas de Java
        return this.fecha.compareTo(otra.fecha);
    }

    /*
     MÉTODOS GETTER
    "Protected Access Modifier":
     Sirven para que otros archivos (como la interfaz gráfica) puedan "leer" el valor de estos datos, ya que los datos en sí están protegidos.
     */
    public String getDescripcion() {
        return descripcion;
    }

    public double getImporte() {
        return importe;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}