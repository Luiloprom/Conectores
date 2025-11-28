package es.etg.dam.acceso.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.etg.dam.acceso.model.Alumno;
import es.etg.dam.acceso.model.InstitutoDAO;

public class InstitutoOracleXeDAOImp implements InstitutoDAO {

    private Connection conn;
    private final String URL = "jdbc:oracle:thin:%s/%s@localhost:1521/instituto";
    private final String DATABASE_USER = "usuario";
    private final String DATABASE_PASS = "usuario";

    public InstitutoOracleXeDAOImp() throws Exception {
        conn = DriverManager.getConnection(String.format(URL, DATABASE_USER, DATABASE_PASS));
    }

    public String testConexion() throws SQLException {
        String sql = "SELECT ? FROM DUAL";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 1); // parámetro de prueba
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return "Conexión OK, resultado = " + rs.getInt(1);
                } else {
                    return "Conexión realizada, pero sin resultado.";
                }
            }
        }
    }

    @Override
    public void crearTablaAlumno() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public void eliminarTablaAlumno() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarTablaAlumno'");
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarAlumnos'");
    }

    @Override
    public List<Alumno> listarAlumnos(int edad) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarAlumnos'");
    }

    @Override
    public int insertar(Alumno a) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int insertar(List<Alumno> alumnos) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int actualizar(Alumno a) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public int borrar(Alumno a) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrar'");
    }

}
