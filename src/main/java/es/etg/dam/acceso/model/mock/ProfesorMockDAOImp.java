package es.etg.dam.acceso.model.mock;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.acceso.dao.ProfesorDAO;
import es.etg.dam.acceso.model.Profesor;

public class ProfesorMockDAOImp implements ProfesorDAO {

    @Override
    public void crearTabla() throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'crearTabla'");
    }

    @Override
    public int insertar(Profesor a) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'insertar'");
    }

    @Override
    public int actualizar(Profesor a) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public List<Profesor> listarAll() throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'listarAll'");
    }

    @Override
    public Profesor obtenerProfesor(Long id) throws SQLException {
        throw new UnsupportedOperationException("Unimplemented method 'obtenerProfesor'");
    }

}
