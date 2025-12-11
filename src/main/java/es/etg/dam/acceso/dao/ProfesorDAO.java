package es.etg.dam.acceso.dao;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.acceso.model.Profesor;

public interface ProfesorDAO extends DAO<Profesor> {

    @Override
    public void crearTabla() throws SQLException;

    @Override
    public int insertar(Profesor a) throws SQLException;

    @Override
    public int actualizar(Profesor a) throws SQLException;

    @Override
    public List<Profesor> listarAll() throws SQLException;

}
