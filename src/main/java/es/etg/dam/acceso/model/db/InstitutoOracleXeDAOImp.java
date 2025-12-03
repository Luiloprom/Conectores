package es.etg.dam.acceso.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.acceso.model.Alumno;
import es.etg.dam.acceso.model.InstitutoDAO;

public class InstitutoOracleXeDAOImp implements InstitutoDAO {

    private final Connection conn;
    private final String URL = "jdbc:oracle:thin:%s/%s@localhost:1521/instituto";
    private final String DATABASE_USER = "usuario";
    private final String DATABASE_PASS = "usuario";

    public InstitutoOracleXeDAOImp() throws Exception {
        conn = DriverManager.getConnection(String.format(URL, DATABASE_USER, DATABASE_PASS));
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        final String query = "SELECT nombre, apellido, edad FROM alumno";

        List<Alumno> alumnos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");

                Alumno a = new Alumno(nombre, apellido, edad);
                alumnos.add(a);
            }
        }

        return alumnos;
    }

    @Override
    public List<Alumno> listarAlumnos(int edad) throws SQLException {
        final String query = "SELECT nombre, apellido, edad FROM alumno WHERE edad = ?";
        List<Alumno> alumnos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, edad);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                    int edadAl = rs.getInt("edad");

                    Alumno a = new Alumno(nombre, apellido, edadAl);
                    alumnos.add(a);
                }
            }
        }
        return alumnos;
    }

    @Override
    public int insertar(Alumno a) throws SQLException {
        final String query = "INSERT INTO alumno (nombre, apellido, edad) VALUES (?,?,?)";

        int numeroRegistros;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getEdad());
            numeroRegistros = ps.executeUpdate();
        }
        return numeroRegistros;
    }

    @Override
    public int insertar(List<Alumno> alumnos) throws SQLException {
        int numeroRegistros = 0;

        for (Alumno alumno : alumnos) {
            numeroRegistros += insertar(alumno);
        }
        return numeroRegistros;
    }

    @Override
    public void crearTablaAlumno() throws SQLException {
        final String query = """
                CREATE TABLE alumno (
                    nombre VARCHAR2(100) NOT NULL,
                    apellido VARCHAR2(100) NOT NULL,
                    edad NUMBER(3) NOT NULL,
                    CONSTRAINT pk_alumno PRIMARY KEY (nombre, apellido)
                )
                """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.execute();
        }
    }

    @Override
    public void eliminarTablaAlumno() throws SQLException {
        final String query = "DROP TABLE alumno";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.execute();
        }
    }

    @Override
    public int actualizar(Alumno a) throws SQLException {
        final String query = "UPDATE alumno SET edad = ? WHERE nombre = ? AND apellido = ?";

        int numRegistros;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, a.getEdad());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            numRegistros = ps.executeUpdate();
        }
        return numRegistros;
    }

    @Override
    public int borrar(Alumno a) throws SQLException {
        final String query = "DELETE FROM alumno WHERE nombre = ? AND apellido = ?";

        int numRegistros;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            numRegistros = ps.executeUpdate();
        }

        return numRegistros;
    }

}
