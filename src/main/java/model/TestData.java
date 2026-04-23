package model;

import java.time.LocalDate;
public class TestData {
  //Lleno un gestor con datos iniciales para pruebas.

    public static void precargarDatos(GestorFinanzas gestor) {

        gestor.añadirOperacion(new Ingreso("Nómina mensual", 2000.0, LocalDate.now().minusDays(30), "La Salle."));
        gestor.añadirOperacion(new Gasto("Alquiler", 800.0, LocalDate.now().minusDays(25), Categoria.HOGAR, MetodoPago.TRANSFERENCIA));
    }
}
