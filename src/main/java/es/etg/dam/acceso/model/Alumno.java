package es.etg.dam.acceso.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Alumno {
    private Long codAlumno;
    private String nombre;
    private String apellido;
    private int edad;
    private int codTutor;

    @Override
    public String toString() {
        return "codAlumno=" + codAlumno + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
                + ", codTutor=" + codTutor + "\n";
    }
}
