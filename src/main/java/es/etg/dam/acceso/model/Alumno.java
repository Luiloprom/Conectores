package es.etg.dam.acceso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_alumn")
    private Long codAlumno;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "edad")
    private int edad;

    @Column(name = "cod_tutor")
    private int codTutor;

    @Override
    public String toString() {
        return "codAlumno=" + codAlumno + ", nombre=" + nombre + ", apellido=" + apellido +
                ", edad=" + edad + ", codTutor=" + codTutor + "\n";
    }
}
