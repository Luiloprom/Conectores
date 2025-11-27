package es.etg.dam.acceso;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        InstitutoDAO institutoDAO = new InstitutoSQLiteDAOImp();

        List<Alumno> alumnos = new ArrayList<>();

        alumnos = institutoDAO.listarAlumnos();

        pintarAlumnos(alumnos);

        List<Alumno> alumnos2 = new ArrayList<>();

        Alumno a = new Alumno("Eva", "Pinilla", 40);
        Alumno b = new Alumno("Pepe", "Cabeza", 19);
        alumnos2.add(a);
        alumnos2.add(b);

        int numeroRegistros = institutoDAO.insertar(alumnos2);

        alumnos = institutoDAO.listarAlumnos();

        pintarAlumnos(alumnos);
        System.out.println(String.format("El numero de alumnos insertados son %d", numeroRegistros));

    }

    public static void pintarAlumnos(List<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            System.out.println("Nombre : " + alumno.getNombre() + " Apellido : " + alumno.getApellido() + " Edad : "
                    + alumno.getEdad());
        }
    }
}