package es.etg.dam.acceso.dao;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.acceso.model.Alumno;

public interface AlumnoDAO extends DAO<Alumno> {

    @Override
    public void crearTabla() throws SQLException;

    @Override
    public int insertar(Alumno a) throws SQLException;

    @Override
    public int actualizar(Alumno a) throws SQLException;

    @Override
    public List<Alumno> listarAll() throws SQLException;

    @Override
    public List<Alumno> listarRelacionados() throws SQLException;

    @Override
    public List<Alumno> consultar(String a) throws SQLException;
}
