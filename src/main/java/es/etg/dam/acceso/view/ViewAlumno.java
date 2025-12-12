package es.etg.dam.acceso.view;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import es.etg.dam.acceso.controller.InstitutoController;
import es.etg.dam.acceso.model.Alumno;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViewAlumno {
    private InstitutoController institutoController;

    // Opcion 2 - Insertar Alumno
    public void insertarAlumno() {
        String nombre = JOptionPane.showInputDialog("Ingresa un nombre : ");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos : ");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad : "));
        int codTutor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del tutor"));

        try {
            institutoController.insertarAlumno(nombre, apellido, edad, codTutor);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No existe un profesor con ese id");
        }
    }

    // Opcion 4 - Actualizar Alumno
    public void actualizarAlumno() {
        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingresa el id del alumno a modificar"));
            if (institutoController.obtenerAlumno(id) != null) {
                String nombre = JOptionPane.showInputDialog("Ingresa un nuevo nombre : ");
                String apellido = JOptionPane.showInputDialog("Ingresa los nuevos apellidos : ");
                int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad : "));
                int codTutor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo codigo del tutor"));
                institutoController.modificarAlumno(id, nombre, apellido, edad, codTutor);
            } else {
                JOptionPane.showMessageDialog(null, String.format("No se a encontrado el alumno con id %d ", id));
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No existe un profesor con ese id");
        }

    }

    // Opcion 6 - Listar Todos los alumnos
    public void listarAlumnos() throws Exception {
        List<Alumno> alumnos = institutoController.listarAlumnos();
        StringBuilder sb = new StringBuilder();
        for (Alumno alumno : alumnos) {
            sb.append(alumno.toString());
        }
        JOptionPane.showMessageDialog(null, sb);
    }

    // Opcion 7 - Listar Alumnos y sus tutores
    public void listarRelacionados() throws SQLException {
        List<String> lineas;
        try {
            lineas = institutoController.listarRelacionados();
            StringBuilder sb = new StringBuilder();
            for (String linea : lineas) {
                sb.append(linea).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb);
        } catch (SQLException e) {
        }
    }

    // Opcion 8 - Listar Alumnos por id
    public void obtenerAlumno() throws SQLException {
        Long id = Long.valueOf(JOptionPane.showInputDialog("Ingresa el id del alumno que quieres buscar"));
        Alumno a = institutoController.obtenerAlumno(id);
        if (a != null) {
            JOptionPane.showMessageDialog(null, a.toString());
        } else {
            JOptionPane.showMessageDialog(null, String.format("El alumno con id %d no existe", id));
        }
    }
}
