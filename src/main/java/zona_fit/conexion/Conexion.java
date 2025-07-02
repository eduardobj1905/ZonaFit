package zona_fit.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class Conexion {

    public static Connection getConexion() {
        Connection conexion = null;

        try {
            // Cargar el archivo de configuración desde el classpath
            Properties props = new Properties();
            InputStream entrada = Conexion.class.getClassLoader().getResourceAsStream("config.properties");

            if (entrada == null) {
                throw new RuntimeException("No se encontró el archivo config.properties");
            }

            props.load(entrada);

            // Obtener valores desde el archivo
            String url = props.getProperty("db.url");
            String usuario = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // Cargar el driver y establecer la conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);

        } catch (Exception e) {
            System.out.println("Error al conectarnos a la BD: " + e.getMessage());
        }

        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if (conexion != null)
            System.out.println("Conexión exitosa: " + conexion);
        else
            System.out.println("Error al conectarse");
    }
}