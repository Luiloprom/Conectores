package es.etg.dam.acceso.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Profesor {
    private Long id;
    private String nombre;
    private String apellido;

    @Override
    public String toString() {
        return "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + "\n";
    }
}
