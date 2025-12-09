package es.etg.dam.acceso.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    void crearTabla() throws SQLException;

    int insertar(T a) throws SQLException;

    int actualizar(T a) throws SQLException;

    List<T> listarAll() throws SQLException;

    List<T> listarRelacionados() throws SQLException;

    List<T> consultar(String a) throws SQLException;

}
