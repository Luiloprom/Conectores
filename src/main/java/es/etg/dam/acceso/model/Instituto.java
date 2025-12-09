package es.etg.dam.acceso.model;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.acceso.dao.AlumnoDAO;

public class Instituto {

    private final AlumnoDAO alumnoDAO;

    public Instituto(AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    public void crearTablaAlumno() throws Exception {
        alumnoDAO.crearTabla();
    };

    public int insertarAlumno(Alumno a) throws SQLException {
        return alumnoDAO.insertar(a);
    };

    public int actualizarAlumno(Alumno a) throws SQLException {
        return alumnoDAO.actualizar(a);
    };

    public List<Alumno> listarAllAlumnos() throws SQLException {
        return alumnoDAO.listarAll();
    };

    public List<Alumno> listarRelacionados() throws SQLException {
        return alumnoDAO.listarRelacionados();
    };

    public List<Alumno> consultar(String a) throws SQLException {
        return alumnoDAO.consultar(a);
    };
}
