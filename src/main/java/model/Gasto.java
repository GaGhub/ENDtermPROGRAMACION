package model;

// Importamos LocalDate porque lo necesita para la fecha
import java.time.LocalDate;

/*
 CLASE GASTO (Clase Hija / Subclase)

 Uso 'extends Operacion' para indicar que Gasto hereda las características de la clase Operacion.
 */
public class Gasto extends Operacion {

    /* ATRIBUTOS ESPECÍFICOS
   Estos datos solo los tienen los Gastos, no los Ingresos. Uso los Enums que creé en el paso anterior como me solicitó la profesora en la corrección del midterm    */
    private Categoria categoria;
    private MetodoPago metodoPago;

    /*
  CONSTRUCTOR 1: COMPLETO (Con descripción)
 Este constructor se usa cuando el usuario escribe una descripción.
     */
    public Gasto(String descripcion, double importe, LocalDate fecha, Categoria categoria, MetodoPago metodoPago) {
        /*
     "Inheritance Super keyword":
      Uso 'super' para llamar al constructor del padre (Operacion). Esto guarda la descripción, el importe y la fecha en la parte "común".
         */
        super(descripcion, importe, fecha);

        // Guardo los datos que son solo de Gasto
        this.categoria = categoria;
        this.metodoPago = metodoPago;
    }

    /*
CONSTRUCTOR 2: SIN DESCRIPCIÓN (Sobrecarga)
 Según la corrección de la profe: mejor si dispongo de dos constructores que permitan crear un Gasto con y sin descripción. Así que lo separo.
     */
    public Gasto(double importe, LocalDate fecha, Categoria categoria, MetodoPago metodoPago) {
        /*
    Llamamos al constructor de arriba (el completo). Al poner 'null' en el primer hueco, indico que no hay descripción.
         */
        this(null, importe, fecha, categoria, metodoPago);
    }

    /*
MÉTODOS GETTER
Me permiten leer la categoría y el métudo de pago desde la interfaz gráfica.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
}