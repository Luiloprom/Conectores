package es.etg.dam.acceso.model.db;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.acceso.model.Alumno;
import es.etg.dam.acceso.model.InstitutoDAO;

public class InstitutoSQLiteDAOImp implements InstitutoDAO {
    private static final String DATABASE_NAME = "es/etg/dam/acceso/mibase.db";
    private static final String JDBC_URL = "jdbc:sqlite:%s";

    private final Connection conn;

    public InstitutoSQLiteDAOImp() throws Exception {
        URL resource = InstitutoSQLiteDAOImp.class.getClassLoader().getResource(DATABASE_NAME);
        String path = new File(resource.toURI()).getAbsolutePath();
        String url = String.format(JDBC_URL, path);
        this.conn = DriverManager.getConnection(url);
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        final String query = "SELECT nombre, apellido, edad FROM alumno";

        List<Alumno> alumnos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
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
        List<Alumno> todos = listarAlumnos();
        List<Alumno> filtrado = new ArrayList<>();
        for (Alumno alumno : todos) {
            if (alumno.getEdad() == edad) {
                filtrado.add(alumno);
            }
        }

        return filtrado;
    }

    @Override
    public int insertar(Alumno a) throws SQLException {
        final String query = "INSERT INTO Alumno (nombre, apellido, edad) VALUES (?,?,?)";

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
    public void crearTablaAlumno() throws Exception {
        final String query = """
                CREATE TABLE IF NOT EXISTS alumno (
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    edad INTEGER NOT NULL,
                    PRIMARY KEY (nombre, apellido)
                )
                """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.execute();
        }
    }

    @Override
    public void eliminarTablaAlumno() throws Exception {
        final String query = "DROP TABLE IF EXISTS alumno";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.execute();
        }
    }

    @Override
    public int actualizar(Alumno a) throws SQLException {
        final String query = "UPDATE alumno SET edad = ? where nombre = ?";
        int numRegistros;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, a.getEdad());
            ps.setString(2, a.getNombre());
            numRegistros = ps.executeUpdate();
        }
        return numRegistros;
    }

    @Override
    public int borrar(Alumno a) throws SQLException {
        final String query = "DELETE FROM alumno where nombre = ?";
        int numRegistros;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, a.getNombre());
            numRegistros = ps.executeUpdate();
        }

        return numRegistros;
    }

}
