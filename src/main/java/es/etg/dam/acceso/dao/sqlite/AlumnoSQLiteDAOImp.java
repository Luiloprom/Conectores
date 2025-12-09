package es.etg.dam.acceso.dao.sqlite;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.acceso.dao.AlumnoDAO;
import es.etg.dam.acceso.model.Alumno;

public class AlumnoSQLiteDAOImp implements AlumnoDAO {
    private static final String DATABASE_NAME = "es/etg/dam/acceso/mibase.db";
    private static final String JDBC_URL = "jdbc:sqlite:%s";

    private final Connection conn;

    public AlumnoSQLiteDAOImp() throws Exception {
        URL resource = AlumnoSQLiteDAOImp.class.getClassLoader().getResource(DATABASE_NAME);
        String path = new File(resource.toURI()).getAbsolutePath();
        String url = String.format(JDBC_URL, path);
        this.conn = DriverManager.getConnection(url);
    }

    @Override
    public void crearTabla() throws SQLException {
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
    public List<Alumno> listarAll() throws SQLException {
        final String query = "SELECT cod_alumn, nombre, apellido, edad, cod_tutor FROM alumno";

        List<Alumno> alumnos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Long codAlumno = rs.getLong("cod_alumn");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                int codTutor = rs.getInt("cod_tutor");

                Alumno a = new Alumno(codAlumno, nombre, apellido, edad, codTutor);
                alumnos.add(a);
            }
        }

        return alumnos;
    }

    @Override
    public List<Alumno> listarRelacionados() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarRelacionados'");
    }

    @Override
    public List<Alumno> consultar(String a) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consultar'");
    }

}
