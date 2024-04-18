package conexion;


import java.sql.*;


public class Conexion {
    static final String URL = "jdbc:mysql://localhost:3306/auto";
    static final String USUARIO = "root";
    static final String CONTRASEÑA = "12345";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }

    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
