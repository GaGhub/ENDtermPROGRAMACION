package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
Esta clase que gestiona la conexión a la base de datos MySQL.
Aplico el Patrón Singleton para garantizar una única instancia de conexión.
 */
public class ConexionDB {

    // Referencia estática a la conexión
    private static Connection conexion = null;

    // Parámetros de configuración de la base de datos (MariaDB/MySQL)
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_finanzas";
    private static final String USER = "root";
    private static final String PASS = ""; // Deja vacío o pon tu contraseña de MySQL


  // Constructor privado: evita que se puedan crear instancias con 'new ConexionDB()"desde fuera de esta clase.

    private ConexionDB() {}

    // Métudo público y estático que devuelve la conexión activa. Si no existe, la crea; si ya existe, devuelve la existente.

    public static Connection getConexion() {
        try {
            // Verifico si la conexión es nula o si se ha cerrado
            if (conexion == null || conexion.isClosed()) {
                // Cargo el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establezco la conexión usando los parámetros definidos arriba
                conexion = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("LOG: Conexión establecida correctamente.");
            }
            //Muestro los errores para los dos casos
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: No se encontró el driver de MySQL. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("ERROR: Error de SQL al intentar conectar. " + e.getMessage());
        }
        return conexion;
    }
}