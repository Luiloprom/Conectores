package es.etg.dam.acceso.view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import es.etg.dam.acceso.controller.InstitutoController;
import es.etg.dam.acceso.model.Alumno;
import es.etg.dam.acceso.model.Modo;

public class ViewController {
    private final String OPC_MOCK = "1 - Mock";
    private final String OPC_SQLITE = "2 - SQlite";
    private final String OPC_ORACLE = "3 - Oracle";
    private final String FORMAT_MENU_CONEXIONES = "Elige una conexion : \n %s \n %s \n %s";

    private final String MENU_CONEXIONES = String.format(FORMAT_MENU_CONEXIONES, OPC_MOCK, OPC_SQLITE, OPC_ORACLE);

    protected InstitutoController institutoController;

    public void setInstitutoController(InstitutoController institutoController) {
        this.institutoController = institutoController;
    }

    public Modo cargarMenuInicial() {
        int respuesta = Integer
                .parseInt(JOptionPane.showInputDialog(MENU_CONEXIONES));
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
                        "Elije una opcion : \n 1 - Crear Tablas \n 2 - Insertar Alumno \n 3 - Insertar Profesor \n 4 - Actualizar Alumno \n 5 - Actualizar Profesor \n 6 - Listar todos los Alumnos \n 7 - Listar todos los alumnos y sus profesores  \n 8 - Consulta libre "));
        switch (respuesta) {
            case 2 -> insertarAlumno();
            case 6 -> listarAlumnos();
            default -> throw new AssertionError();
        }
    }

    // Opcion 2 - Insertar Alumno
    public void insertarAlumno() throws SQLException {
        String nombre = JOptionPane.showInputDialog("Ingresa un nombre : ");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos : ");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad : "));
        int codTutor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del tutor"));

        institutoController.insertarAlumno(nombre, apellido, edad, codTutor);
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
}
