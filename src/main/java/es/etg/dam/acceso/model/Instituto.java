package es.etg.dam.acceso.model;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.acceso.model.db.alumno.AlumnoDAO;

public class Instituto {

    private final AlumnoDAO alumnoDAO;

    public Instituto(AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    public void crearTablaAlumno() throws Exception {
        alumnoDAO.crearTablaAlumno();
    };

    public void eliminarTablaAlumno() throws Exception {
        alumnoDAO.eliminarTablaAlumno();
    };

    public List<Alumno> listarAlumnos() throws SQLException {
        return alumnoDAO.listarAlumnos();
    };

    public List<Alumno> listarAlumnos(int edad) throws SQLException {
        return alumnoDAO.listarAlumnos();
    };

    public int insertar(Alumno a) throws SQLException {
        return alumnoDAO.insertar(a);
    };

    public int insertar(List<Alumno> alumnos) throws SQLException {
        return alumnoDAO.insertar(alumnos);
    };

    public int actualizar(Alumno a) throws SQLException {
        return alumnoDAO.actualizar(a);
    };

    public int borrar(Alumno a) throws SQLException {
        return alumnoDAO.borrar(a);
    };
}
