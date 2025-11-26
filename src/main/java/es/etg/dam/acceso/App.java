package es.etg.dam.acceso;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        InstitutoDAO institutoDAO = new InstitutoSQLiteDAOImp();

        List<Alumno> alumnos = new ArrayList<>();

        alumnos = institutoDAO.listarAlumnos();

        for (Alumno alumno : alumnos) {
            System.out.println("Nombre : " + alumno.getNombre() + " Apellido : " + alumno.getApellido() + " Edad : "
                    + alumno.getEdad());
        }

        Alumno a = new Alumno("David", "Hoyas", 19);
        institutoDAO.insertar(a);

        alumnos = institutoDAO.listarAlumnos();

        for (Alumno alumno : alumnos) {
            System.out.println("Nombre : " + alumno.getNombre() + " Apellido : " + alumno.getApellido() + " Edad : "
                    + alumno.getEdad());
        }

    }
}