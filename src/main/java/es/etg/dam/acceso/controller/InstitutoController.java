package es.etg.dam.acceso.controller;

import java.sql.SQLException;
import java.util.List;

import es.etg.dam.acceso.dao.AlumnoDAO;
import es.etg.dam.acceso.dao.ProfesorDAO;
import es.etg.dam.acceso.model.Alumno;
import es.etg.dam.acceso.model.Instituto;
import es.etg.dam.acceso.model.InstitutoFactory;
import es.etg.dam.acceso.model.Modo;
import es.etg.dam.acceso.model.Profesor;
import es.etg.dam.acceso.view.ViewController;

public class InstitutoController {

    private final ViewController viewController;
    private Instituto instituto;

    public InstitutoController(ViewController viewController) {
        this.viewController = viewController;
    }

    public void empezar() throws Exception {
        viewController.setInstitutoController(this);
        Modo modo = viewController.cargarMenuInicial();
        AlumnoDAO alumnoDAO = InstitutoFactory.obtenerAlumnoDAO(modo);
        ProfesorDAO profesorDAO = InstitutoFactory.obtenerProfesorDAO(modo);
        instituto = new Instituto(alumnoDAO, profesorDAO);
        viewController.cargarMenuOpciones();
    }

    // Metodos de alumno
    public List<Alumno> listarAlumnos() throws SQLException {
        return instituto.listarAllAlumnos();
    }

    public void insertarAlumno(String nombre, String apellidos, int edad, int cod_tutor) throws SQLException {
        Alumno al = new Alumno(null, nombre, apellidos, edad, cod_tutor);

        instituto.insertarAlumno(al);
    }

    public void modificarAlumno(Long id, String nombre, String apellidos, int edad, int cod_tutor) throws SQLException {
        Alumno al = new Alumno(id, nombre, apellidos, edad, cod_tutor);

        instituto.actualizarAlumno(al);
    }

    public Alumno obtenerAlumno(Long id) throws SQLException {
        return instituto.obtenerAlumno(id);
    }

    public List<String> listarRelacionados() throws SQLException {
        return instituto.listarRelacionados();
    }

    // Metodos de profesor
    public int insertarProfesor(String nombre, String apellido) throws SQLException {
        Profesor p = new Profesor(null, nombre, apellido);
        return instituto.actualizarProfesor(p);
    }

    public Profesor obtenerProfesor(Long id) throws SQLException {
        return instituto.obtenerProfesor(id);
    }

    public int modificarProfesor(Long id, String nombre, String apellido) throws SQLException {
        Profesor p = new Profesor(id, nombre, apellido);
        return instituto.actualizarProfesor(p);
    }

    public List<Profesor> listarProfesores() throws SQLException {
        return instituto.listarProfesores();
    }

}
