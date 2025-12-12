package es.etg.dam.acceso.dao.sqlite;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {
    private static final String DATABASE_NAME = "es/etg/dam/acceso/mibase.db";
    private static final String JDBC_URL = "jdbc:sqlite:%s";

    private final Connection conn;
    private static ConexionSQLite conexionSQLite;

    public static ConexionSQLite obtenerConexion() throws SQLException, URISyntaxException {
        if (conexionSQLite == null) {
            conexionSQLite = new ConexionSQLite();
        }
        return conexionSQLite;
    }

    private ConexionSQLite() throws SQLException, URISyntaxException {
        URL resource = AlumnoSQLiteDAOImp.class.getClassLoader().getResource(DATABASE_NAME);
        String path = new File(resource.toURI()).getAbsolutePath();
        String url = String.format(JDBC_URL, path);
        this.conn = DriverManager.getConnection(url);
    }

    public Connection getConn() {
        return conn;
    }
    
}
