
package com.sgos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Datos de conexión a MySQL.
    private static final String URL =
        "jdbc:mysql://localhost:3306/sgos";

    private static final String USUARIO = "root";

    // Se obtiene la contraseña desde una variable de entorno.
    private static final String PASSWORD =
        System.getenv("DB_PASSWORD");

    // Método para obtener una conexión JDBC.
    public static Connection getConnection()
            throws SQLException {

        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new SQLException(
                "Configura la variable de entorno DB_PASSWORD");
        }

        return DriverManager.getConnection(
            URL, USUARIO, PASSWORD);
    }
}
