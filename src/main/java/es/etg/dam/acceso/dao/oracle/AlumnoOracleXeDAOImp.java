package es.etg.dam.acceso.dao.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory;

import es.etg.dam.acceso.dao.AlumnoDAO;
import es.etg.dam.acceso.model.Alumno;

public class AlumnoOracleXeDAOImp implements AlumnoDAO {

    private final Connection conn;
    private final String URL = "jdbc:oracle:thin:%s/%s@localhost:1521/instituto";
    private final String DATABASE_USER = "usuario";
    private final String DATABASE_PASS = "usuario";

    public AlumnoOracleXeDAOImp() throws Exception {
        conn = DriverManager.getConnection(String.format(URL, DATABASE_USER, DATABASE_PASS));
    }

    @Override
    public void crearTabla() throws SQLException {
        final String query = """
                CREATE TABLE alumno (
                    cod_alumn NUMBER(2) PRIMARY KEY,
                    nombre    VARCHAR2(30) NOT NULL,
                    apellido  VARCHAR2(30),
                    edad      NUMBER(2),
                    cod_tutor NUMBER(2)
                )
                """;
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.execute();
        }
    }

    @Override
    public int  insertar(Alumno a) throws SQLException {
        final String query = """
                INSERT INTO alumno (nombre, apellido, edad, cod_tutor)
                VALUES (?,?,?,?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getEdad());
            ps.setInt(4, a.getCodTutor());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                a.setCodAlumno(rs.getLong(1));
            }
            
            return 1;
        }
    }

    @Override
    public int actualizar(Alumno a) throws SQLException {
        final String query = """
                UPDATE alumno
                SET nombre = ?, apellido = ?, edad = ?, cod_tutor = ?
                WHERE cod_alumn = ?
                """;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getEdad());
            ps.setInt(4, a.getCodTutor());
            ps.setLong(5, a.getCodAlumno());
            return ps.executeUpdate();
        }
    }

    @Override
    public List<Alumno> listarAll() throws SQLException {
        final String query = "SELECT cod_alumn, nombre, apellido, edad, cod_tutor FROM alumno";

        List<Alumno> alumnos = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
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

    public Alumno obtenerAlumno(int id) throws SQLException{
        final String query = "SELECT cod_alumn, nombre, apellido, edad, cod_tutor FROM alumno WHERE cod_num = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
            if (rs.next()))
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
