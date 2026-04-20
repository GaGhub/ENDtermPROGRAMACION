package console.model;

import java.time.LocalDate;  // Importante para usar fechas


public class Gasto implements java.io.Serializable {
    // Primero voy a crear los atributos, que son las características de la clase

    private String descripcion;
    private double importe;
    private LocalDate fecha;
    private String categoria;
    private String metodoPago;

    // Luego como constructor, genero la forma en la que se crea un gasto, de forma que cuando hagamos "nuevo Gasto()" le pasaré todos los datos de golpe.

    public Gasto(String descripcion, double importe, LocalDate fecha, String categoria, String metodoPago) //Aquí llegan los datos que se introducen por el teclado
    {
        this.descripcion = descripcion; //Aquí guardo los datos, como si fueran cajas
        this.importe = importe; // Ejempplo: Significa "La variable que pertenece a esta ficha es el valor que te acaban de pasar por el teclado es el teclado
        this.fecha = fecha;
        this.categoria = categoria;
        this.metodoPago = metodoPago;
    }

    // Cuando hablamos de Métodos, tenemos los getters y setters. Los primeros son la forma de poder leer un valor y el segundo para modificarlo.
    //Getter = "Dime que valor tienes"
    //Setter = "Actualiza tu valor"

 //Getter Descripción
    public String obtenerDescripcion() {
        return descripcion;
    }
//Setter descripcion
    public void fijarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//Getter Importe
    public double obtenerImporte() {
        return importe;
    }
//SetterImporte
    public void fijarImporte(double importe) {
        this.importe = importe;
    }

    //Getter Fecha
    public LocalDate obtenerFecha() {
        return fecha;
    }
    //Setter fecha
    public void fijarFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    //Getter categoria
    public String obtenerCategoria() {
        return categoria;
    }
    //Setter categoria
    public void fijarCategoria(String categoria) {
        this.categoria = categoria;
    }

    //Getter Métudo de pago
    public String obtenerMetodoPago() {
        return metodoPago;
    }
    //Setter Métudo de pago
    public void fijarMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    // El métudo toString: Le digo a la consola cómo imprimir el gasto en una línea.
    @Override
    public String toString() {
        return "GASTO -> Fecha: " + fecha + " | " + descripcion + " - " + importe + "€ - [" + categoria + "] - Pago por: " + metodoPago;
    }
}

//Con esto finalizo la creación de la primera clase (gasto)