package es.etg.dam.acceso.model.mock;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.acceso.dao.AlumnoDAO;
import es.etg.dam.acceso.model.Alumno;

public class AlumnoMockDAOImp implements AlumnoDAO {

    @Override
    public void crearTabla() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearTabla'");
    }

    @Override
    public int insertar(Alumno a) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int actualizar(Alumno a) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public List<Alumno> listarAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarAll'");
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
