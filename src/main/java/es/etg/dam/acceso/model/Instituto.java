package es.etg.dam.acceso.model;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.acceso.dao.AlumnoDAO;
import es.etg.dam.acceso.dao.ProfesorDAO;

public class Instituto {

    private final AlumnoDAO alumnoDAO;
    private final ProfesorDAO profesorDAO;

    public Instituto(AlumnoDAO alumnoDAO, ProfesorDAO profesorDAO) {
        this.alumnoDAO = alumnoDAO;
        this.profesorDAO = profesorDAO;
    }

    // Metodos para Alumno
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

    public List<String> listarRelacionados() throws SQLException {
        return alumnoDAO.listarRelacionados();
    };

    public Alumno obtenerAlumno(Long id) throws SQLException {
        return alumnoDAO.obtenerAlumno(id);
    }

    // Metodos para Profesor
    public void crearTablaProfesor() throws SQLException {
        profesorDAO.crearTabla();
    }

    public int insertarProfesor(Profesor p) throws SQLException {
        return profesorDAO.insertar(p);
    }

    public int actualizarProfesor(Profesor p) throws SQLException {
        return profesorDAO.actualizar(p);
    }

    public List<Profesor> listarProfesores() throws SQLException {
        return profesorDAO.listarAll();
    }
}
