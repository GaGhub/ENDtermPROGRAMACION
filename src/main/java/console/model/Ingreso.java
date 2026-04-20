package console.model;

import java.time.LocalDate;      // Importante para usar fechas


public class Ingreso implements java.io.Serializable {

 //Igual que he hecho antes en gasto, voy a crear primero los atributos y sus características.

    private String descripcion;
    private double importe;
    private LocalDate fecha;
    private String origen;

    //Continuamos con el constructor:

    public Ingreso (String descripcion, double importe, LocalDate fecha, String origen) {
        this.descripcion = descripcion;
        this.importe = importe;
        this.fecha = fecha;
        this.origen = origen;
    }

     // Finalmente creamos los Getters y los Setters de cada una de ellas:

    //Getter = "Dime que valor tienes"
    //Setter = "Actualiza tu valor"

    //Getter descripcion
    public String obtenerDescripcion() {return descripcion; }  //Muy importante no olvidarme las llaves
   //Setter descripcion
    public void fijarDescripcion (String descripcion) {this.descripcion = descripcion;}

    //Getter Importe
    public double obtenerImporte() {
        return importe;
    }
    //Setter Importe
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

    //Getter origen
    public String obtenerOrigen() {
        return origen;
    }
    //Setter origen
    public void fijarOrigen(String origen) {this.origen = origen; }

    // El métudo toString: me servirá para que la consola quiera imprimir el gasto, sepa cómo escribirlo en una línea.
    @Override
    public String toString() {
        return "INGRESO -> Fecha: " + fecha + " | " + descripcion + " - " + importe + "€, proveniente de: [" + origen + "]";
    }
}

// Con esto finalizo la clase de Ingreso



