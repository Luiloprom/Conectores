package es.etg.dam.acceso.view;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import es.etg.dam.acceso.controller.InstitutoController;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViewProfesor {
    private InstitutoController institutoController;

    public void insertarProfesor() throws SQLException {
        Long id = Long.valueOf(JOptionPane.showInputDialog("Ingresa un id : "));
        String nombre = JOptionPane.showInputDialog("Ingresa un nombre : ");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos : ");

        int compro = institutoController.insertarProfesor(id, nombre, apellido);
        if (compro != 1) {
            JOptionPane.showMessageDialog(null, "Ese id ya esta asignado a otro profesor");
        }
    }
}
