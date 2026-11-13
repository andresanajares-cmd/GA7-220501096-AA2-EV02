
package com.sgos.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/sgos";
    private static final String USER = "root";

    public static Connection getConnection() throws SQLException {

        String password = System.getenv("DB_PASSWORD");

        if (password == null || password.isBlank()) {
            throw new SQLException(
                "La variable de entorno DB_PASSWORD no está configurada"
            );
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                "No se encontró el controlador JDBC de MySQL", e
            );
        }

        return DriverManager.getConnection(
            URL,
            USER,
            password
        );
    }
}
