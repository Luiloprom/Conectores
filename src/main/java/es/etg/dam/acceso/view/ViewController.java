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

    private final String OPC_1 = "1 - Crear Tablas";
    private final String OPC_2 = "2 - Insertar Alumno";
    private final String OPC_3 = "3 - Insertar Profesor";
    private final String OPC_4 = "4 - Actualizar Alumno";
    private final String OPC_5 = "5 - Actualizar Profesor";
    private final String OPC_6 = "6 - Listar todos los Alumnos";
    private final String OPC_7 = "7 - Listar Alumnos y sus tutores";
    private final String OPC_8 = "8 - Buscar alumno por id";
    private final String OPC_9 = "9 - Salir";

    private final String FORMAT_MENU_PRINCIPAL = "Elige una opcion : \n %s \n %s \n %s \n %s \n %s \n %s \n %s \n %s \n %s";
    private final String MENU_PRINCIPAL = String.format(FORMAT_MENU_PRINCIPAL,OPC_1, OPC_2, OPC_3, OPC_4, OPC_5, OPC_6, OPC_7, OPC_8, OPC_9);


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
        Boolean salir = false;
        while (!salir) { 
            int respuesta = Integer
                .parseInt(JOptionPane.showInputDialog(MENU_PRINCIPAL));
        switch (respuesta) {
            case 2 -> insertarAlumno();
            case 4 -> actualizarAlumno();
            case 6 -> listarAlumnos();
            case 7 -> listarRelacionados();
            case 8 -> obtenerAlumno();
            case 9 -> salir = true;
            default -> throw new AssertionError();
        }
        }
        
    }

    // Opcion 2 - Insertar Alumno
    private void insertarAlumno() throws SQLException {
        String nombre = JOptionPane.showInputDialog("Ingresa un nombre : ");
        String apellido = JOptionPane.showInputDialog("Ingresa los apellidos : ");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad : "));
        int codTutor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del tutor"));

        institutoController.insertarAlumno(nombre, apellido, edad, codTutor);
    }

    // Opcion 4 - Actualizar Alumno
    private void actualizarAlumno() throws SQLException{
        Long id = Long.valueOf(JOptionPane.showInputDialog("Ingresa el id del alumno a modificar"));
        if (institutoController.obtenerAlumno(id) != null){
            String nombre = JOptionPane.showInputDialog("Ingresa un nuevo nombre : ");
            String apellido = JOptionPane.showInputDialog("Ingresa los nuevos apellidos : ");
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva edad : "));
            int codTutor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo codigo del tutor"));
            institutoController.modificarAlumno(id, nombre, apellido, edad, codTutor);
        } else {
            JOptionPane.showMessageDialog(null, String.format("No se a encontrado el alumno con id %d ", id));
        }

    }

    // Opcion 6 - Listar Todos los alumnos
    private void listarAlumnos() throws Exception {
        List<Alumno> alumnos = institutoController.listarAlumnos();
        StringBuilder sb = new StringBuilder();
        for (Alumno alumno : alumnos) {
            sb.append(alumno.toString());
        }
        JOptionPane.showMessageDialog(null, sb);
    }

    // Opcion 7 - Listar Alumnos y sus tutores 
    private void listarRelacionados() throws SQLException {
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

    // Opcion 8 - Listar Alumnos por idç
    private void obtenerAlumno() throws SQLException {
        Long id = Long.valueOf(JOptionPane.showInputDialog("Ingresa el id del alumno que quieres buscar"));
        Alumno a = institutoController.obtenerAlumno(id);
        if (a != null) {
            JOptionPane.showMessageDialog(null, a.toString());
        } else {
            JOptionPane.showMessageDialog(null, String.format("El alumno con id %d no existe", id));
        }
    }
}
