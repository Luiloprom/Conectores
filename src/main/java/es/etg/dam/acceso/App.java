package es.etg.dam.acceso;

import java.util.List;

import es.etg.dam.acceso.model.Alumno;
import es.etg.dam.acceso.model.InstitutiDAOFactory;
import es.etg.dam.acceso.model.InstitutoDAO;
import es.etg.dam.acceso.model.Modo;

public class App {
    public static void main(String[] args) throws Exception {
        InstitutoDAO institutoDAO = InstitutiDAOFactory.obtenerModo(Modo.SQLITE);

    }

    public static void pintarAlumnos(List<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            System.out.println("Nombre : " + alumno.getNombre() + " Apellido : " + alumno.getApellido() + " Edad : "
                    + alumno.getEdad());
        }
    }
}