package dao;

import model.*;
import java.sql.*;
import java.util.TreeSet;

/**
 * Implementación concreta del acceso a datos usando JDBC.
 */
public class DaoOperacionImplementacion implements DaoOperacion {

    @Override
    public boolean insertar(Operacion op) {
        // Consulta SQL con marcadores '?' para mayor seguridad
        String sql = "INSERT INTO operaciones (tipo, descripcion, importe, fecha, origen, metodo_pago) VALUES (?, ?, ?, ?, ?, ?)";

        // Bloque try-with-resources para asegurar el cierre de la conexión
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Asignación de parámetros basándose en el tipo de objeto (Polimorfismo)
            ps.setString(1, (op instanceof Gasto) ? "GASTO" : "INGRESO");
            ps.setString(2, op.getDescripcion());
            ps.setDouble(3, op.getImporte());
            ps.setDate(4, Date.valueOf(op.getFecha()));
            ps.setString(5, op.getOrigen());

            // Gestión específica para campos que pueden ser nulos o exclusivos de un tipo
            if (op instanceof Gasto) {
                ps.setString(6, ((Gasto) op).getMetodoPago().toString());
            } else {
                ps.setNull(6, Types.VARCHAR);
            }

            // Ejecución de la sentencia
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            // Manejo de errores de base de datos
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public TreeSet<Operacion> listarTodas() {
        TreeSet<Operacion> lista = new TreeSet<>();
        String sql = "SELECT * FROM operaciones";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String desc = rs.getString("descripcion");
                double imp = rs.getDouble("importe");
                java.time.LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String origen = rs.getString("origen");

                // Reconstrucción de los objetos a partir de los datos de la fila
                if ("GASTO".equals(tipo)) {
                    MetodoPago mp = MetodoPago.valueOf(rs.getString("metodo_pago"));
                    Categoria cat = Categoria.valueOf(origen.toUpperCase());
                    lista.add(new Gasto(desc, imp, fecha, cat, mp));
                } else {
                    lista.add(new Ingreso(desc, imp, fecha, origen));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar: " + e.getMessage());
        }
        return lista;
    }
}