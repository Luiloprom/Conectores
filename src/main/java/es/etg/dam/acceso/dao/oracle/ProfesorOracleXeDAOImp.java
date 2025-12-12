package es.etg.dam.acceso.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.etg.dam.acceso.dao.ProfesorDAO;
import es.etg.dam.acceso.model.Profesor;

public class ProfesorOracleXeDAOImp implements ProfesorDAO {

    private final Connection conn;

    public ProfesorOracleXeDAOImp() throws SQLException {
        this.conn = ConexionOracle.obtenerConexion().getConn();
    }

    @Override
    public void crearTabla() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTabla'");
    }

    @Override
    public int insertar(Profesor p) throws SQLException {
        final String query = """
                INSERT INTO profesor (nombre, apellido)
                VALUES (?,?)
                """;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                p.setId(rs.getLong(1));
            }

            return 1;
        }
    }

    @Override
    public int actualizar(Profesor p) throws SQLException {
        final String query = """
                UPDATE profesor
                SET nombre = ?, apellido = ?
                WHERE cod_prof = ?
                """;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setLong(3, p.getId());
            return ps.executeUpdate();
        }
    }

    @Override
    public List<Profesor> listarAll() throws SQLException {
        final String query = "SELECT cod_prof, nombre, apellido from profesor";

        List<Profesor> profesores = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                profesores.add(new Profesor(id, nombre, apellido));
            }
        }
        return profesores;
    }

    @Override
    public Profesor obtenerProfesor(Long id) throws SQLException {
        final String query = "SELECT cod_prof, nombre, apellido FROM profesor WHERE cod_prof = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
                if (rs.next()){
                    Long cod = rs.getLong("cod_prof");
                    String nombre = rs.getString("nombre");
                    String apellido = rs.getString("apellido");
                return new Profesor(cod, nombre, apellido);
                }
        }
        return null;
    }
}
