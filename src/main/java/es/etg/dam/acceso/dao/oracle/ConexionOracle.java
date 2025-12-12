package es.etg.dam.acceso.dao.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {
    private final String URL = "jdbc:oracle:thin:%s/%s@localhost:1521/instituto";
    private final String DATABASE_USER = "usuario";
    private final String DATABASE_PASS = "usuario";

    private final Connection conn;
    
    private static ConexionOracle conexionOracle;

    public static ConexionOracle obtenerConexion() throws SQLException {
        if (conexionOracle == null) {
            conexionOracle = new ConexionOracle();
        }
        return conexionOracle;
    }

    private ConexionOracle() throws SQLException {
        this.conn = DriverManager.getConnection(String.format(URL, DATABASE_USER, DATABASE_PASS));
    }

    public Connection getConn() {
        return conn;
    }
}
