/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import conexion.Conexion;
   import java.sql.*;

public class OperacionesBD {
    public static void insertarAuto(String marca, String modelo, String color, int cilindros) throws SQLException {
        String sql = "INSERT INTO autos (marca, modelo, color, cilindros) VALUES (?, ?, ?, ?)";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, marca);
            statement.setString(2, modelo);
            statement.setString(3, color);
            statement.setInt(4, cilindros);
            statement.executeUpdate();
            System.out.println("Nuevo auto insertado.");
        }
    }

    public static void eliminarAuto(int id) throws SQLException {
        String sql = "DELETE FROM autos WHERE id = ?";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Auto eliminado.");
            } else {
                System.out.println("No se encontró ningún auto con ese ID.");
            }
        }
    }

    public static void modificarAuto(int id, String nuevaMarca, String nuevoModelo, String nuevoColor, int nuevosCilindros) throws SQLException {
        String sql = "UPDATE autos SET marca = ?, modelo = ?, color = ?, cilindros = ? WHERE id = ?";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nuevaMarca);
            statement.setString(2, nuevoModelo);
            statement.setString(3, nuevoColor);
            statement.setInt(4, nuevosCilindros);
            statement.setInt(5, id);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Auto modificado.");
            } else {
                System.out.println("No se encontró ningún auto con ese ID.");
            }
        }
    }

    public static void consultarAutos() throws SQLException {
        String sql = "SELECT * FROM autos";
        try (Connection conexion = Conexion.obtenerConexion();
             Statement statement = conexion.createStatement();
             ResultSet resultados = statement.executeQuery(sql)) {
            System.out.println("Autos:");
            while (resultados.next()) {
                int id = resultados.getInt("id");
                String marca = resultados.getString("marca");
                String modelo = resultados.getString("modelo");
                String color = resultados.getString("color");
                int cilindros = resultados.getInt("cilindros");
                System.out.println("ID: " + id + ", Marca: " + marca + ", Modelo: " + modelo + ", Color: " + color + ", Cilindros: " + cilindros);
            }
        }
    }

    public static void consultarAutoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM autos WHERE id = ?";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultados = statement.executeQuery()) {
                if (resultados.next()) {
                    String marca = resultados.getString("marca");
                    String modelo = resultados.getString("modelo");
                    String color = resultados.getString("color");
                    int cilindros = resultados.getInt("cilindros");
                    System.out.println("ID: " + id + ", Marca: " + marca + ", Modelo: " + modelo + ", Color: " + color + ", Cilindros: " + cilindros);
                } else {
                    System.out.println("No se encontró ningún auto con ese ID.");
                }
            }
        }
    }

    public static void consultarAutosPorMarca(String marca) throws SQLException {
        String sql = "SELECT * FROM autos WHERE marca = ?";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, marca);
            try (ResultSet resultados = statement.executeQuery()) {
                mostrarResultadosConsulta(resultados);
            }
        }
    }

    public static void consultarAutosPorModelo(String modelo) throws SQLException {
        String sql = "SELECT * FROM autos WHERE modelo = ?";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, modelo);
            try (ResultSet resultados = statement.executeQuery()) {
                mostrarResultadosConsulta(resultados);
            }
        }
    }

    public static void consultarAutosPorColor(String color) throws SQLException {
        String sql = "SELECT * FROM autos WHERE color = ?";
        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, color);
            try (ResultSet resultados = statement.executeQuery()) {
                mostrarResultadosConsulta(resultados);
            }
        }
    }

    private static void mostrarResultadosConsulta(ResultSet resultados) throws SQLException {
        if (!resultados.isBeforeFirst()) {
            System.out.println("No se encontraron autos con los criterios especificados.");
        } else {
            System.out.println("Resultados de la consulta:");
            while (resultados.next()) {
                int id = resultados.getInt("id");
                String marca = resultados.getString("marca");
                String modelo = resultados.getString("modelo");
                String color = resultados.getString("color");
                int cilindros = resultados.getInt("cilindros");
                System.out.println("ID: " + id + ", Marca: " + marca + ", Modelo: " + modelo + ", Color: " + color + ", Cilindros: " + cilindros);
            }
        }
    }
}

  



