package es.etg.dam.acceso;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstitutoSQLiteDAOImp implements InstitutoDAO {
    private static final String DATABASE_NAME = "mibase.db";
    private static final String JDBC_URL = "jdbc:sqlite:%s";

    private final Connection conn;

    public InstitutoSQLiteDAOImp() throws Exception {
        URL resource = InstitutoSQLiteDAOImp.class.getResource(DATABASE_NAME);
        String path = new File(resource.toURI()).getAbsolutePath();
        String url = String.format(JDBC_URL, path);
        this.conn = DriverManager.getConnection(url);
    }

    @Override
    public List<Alumno> listarAlumnos() throws SQLException {
        final String query = "SELECT nombre, apellido, edad FROM alumno";

        List<Alumno> alumnos = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");

            Alumno a = new Alumno(nombre, apellido, edad);
            alumnos.add(a);
        }

        rs.close();
        ps.close();

        return alumnos;
    }

    @Override
    public int insertar(Alumno a) throws SQLException {
        final String query = "INSERT INTO Alumno (nombre, apellido, edad) VALUES (?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, a.getNombre());
        ps.setString(2, a.getApellido());
        ps.setInt(3, a.getEdad());
        ps.executeUpdate();

        return 1;
    }

    @Override
    public void crearTablaAlumno() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'crearTablaAlumno'");
    }

    @Override
    public void eliminarTablaAlumno() throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'eliminarTablaAlumno'");
    }

    @Override
    public List<Alumno> listarAlumnos(int edad) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'listarAlumnos'");
    }

    @Override
    public int insertar(List<Alumno> alumnos) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int actualizar(Alumno a) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public int borrar(Alumno a) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'borrar'");
    }

}
