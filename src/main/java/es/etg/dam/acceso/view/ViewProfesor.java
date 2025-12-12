package es.etg.dam.acceso.view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import es.etg.dam.acceso.controller.InstitutoController;
import es.etg.dam.acceso.model.Profesor;
import lombok.Data;

@Data
public class ViewProfesor {
    private InstitutoController institutoController;

    public ViewProfesor(InstitutoController institutoController) {
        this.institutoController = institutoController;
    }

    // Opcion 3 - Insertar Profesor
    public void insertarProfesor() throws SQLException {
        String nombre = JOptionPane.showInputDialog("Ingresa un nombre : ");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos : ");

        int compro = institutoController.insertarProfesor(nombre, apellido);
        if (compro != 1) {
            JOptionPane.showMessageDialog(null, "Ese id ya esta asignado a otro profesor");
        }
    }

    // Opcion 5 - Modificar Profesor
    public void modificarProfesor() throws SQLException {
        Long id = Long.valueOf(JOptionPane.showInputDialog("Ingresa el id del profesor a modificar"));
        if (institutoController.obtenerProfesor(id) != null) {
            String nombre = JOptionPane.showInputDialog("Ingresa un nuevo nombre : ");
            String apellido = JOptionPane.showInputDialog("Ingresa los nuevos apellidos : ");
            int compro = institutoController.modificarProfesor(id, nombre, apellido);
            if (compro != 1) {
                JOptionPane.showMessageDialog(null, "No se a modificado ninguna linea");
            }
        } else {
            JOptionPane.showMessageDialog(null, String.format("No se a encontrado el profesor con id %d ", id));
        }
    }

    // Opcion 7 - Listar todos los profesores
    public void listarAll() throws SQLException {
        List<Profesor> profesores = institutoController.listarProfesores();
        StringBuilder sb = new StringBuilder();
        for (Profesor profesor : profesores) {
            sb.append(profesor.toString());
        }
        JOptionPane.showMessageDialog(null, sb);
    }
}
