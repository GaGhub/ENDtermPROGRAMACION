package dao;

import model.Operacion;
import java.util.TreeSet;

/*
Interfaz que define las operaciones permitidas sobre la base de datos. Actúa como un contrato que cualquier implementación debe cumplir.
 */
public interface DaoOperacion {
    /*
  Inserta un objeto en la tabla correspondiente.
  @param op Objeto de tipo Operacion (puede ser Gasto o Ingreso).
  @return true si la operación se realizó con éxito.
     */
    boolean insertar(Operacion op);
    /*
  Recupera todos los registros almacenados.
  @return Un TreeSet con los objetos, ordenados según su lógica interna.
     */
    TreeSet<Operacion> listarTodas();
}