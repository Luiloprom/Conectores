package es.etg.dam.acceso.view;

import java.util.List;

import javax.swing.JOptionPane;

import es.etg.dam.acceso.controller.InstitutoController;
import es.etg.dam.acceso.model.Alumno;
import es.etg.dam.acceso.model.Modo;

public class ViewController {

    protected InstitutoController institutoController;

    public void setInstitutoController(InstitutoController institutoController) {
        this.institutoController = institutoController;
    }

    public Modo cargarMenuInicial() {
        int respuesta = Integer
                .parseInt(JOptionPane.showInputDialog("Elije una opcion : \n 1 - Mock \n 2 - SQLITE \n 3 - Oracle "));
        return switch (respuesta) {
            case 1 -> Modo.MOCK;
            case 2 -> Modo.SQLITE;
            case 3 -> Modo.ORACLE;
            default -> Modo.MOCK;
        };
    }

    public void cargarMenuOpciones() throws Exception {
        int respuesta = Integer
                .parseInt(JOptionPane.showInputDialog(
                        "Elije una opcion : \n 1 - Listar Alumnos \n 2 - Listar por edad \n 3 - Alumno "));
        switch (respuesta) {
            case 1 -> listarAlumnos();
            default -> throw new AssertionError();
        }
    }

    public void listarAlumnos() throws Exception {
        List<Alumno> alumnos = institutoController.listarAlumnos();
        StringBuilder sb = new StringBuilder();
        for (Alumno alumno : alumnos) {
            sb.append(alumno.getNombre()).append(alumno.getApellido()).append(alumno.getEdad()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb);
    }
}
