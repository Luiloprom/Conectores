package es.etg.dam.acceso.view;

import javax.swing.JOptionPane;

import es.etg.dam.acceso.controller.InstitutoController;
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
    private final String OPC_7 = "7 - Listar todos los Profesores";
    private final String OPC_8 = "8 - Listar Alumnos y sus tutores";
    private final String OPC_9 = "9 - Buscar alumno por id";
    private final String OPC_10 = "10 - Salir";

    private final String FORMAT_MENU_PRINCIPAL = "Elige una opcion : \n %s \n %s \n %s \n %s \n %s \n %s \n %s \n %s \n %s \n %s";
    private final String MENU_PRINCIPAL = String.format(FORMAT_MENU_PRINCIPAL, OPC_1, OPC_2, OPC_3, OPC_4, OPC_5, OPC_6,
            OPC_7, OPC_8, OPC_9, OPC_10);

    protected InstitutoController institutoController;
    private ViewProfesor viewProfesor;
    private ViewAlumno viewAlumno;

    public void setInstitutoController(InstitutoController institutoController) {
        this.institutoController = institutoController;
        viewProfesor = new ViewProfesor(institutoController);
        viewAlumno = new ViewAlumno(institutoController);
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
                case 1 -> institutoController.crearTablas();
                case 2 -> viewAlumno.insertarAlumno();
                case 3 -> viewProfesor.insertarProfesor();
                case 4 -> viewAlumno.actualizarAlumno();
                case 5 -> viewProfesor.modificarProfesor();
                case 6 -> viewAlumno.listarAlumnos();
                case 7 -> viewProfesor.listarAll();
                case 8 -> viewAlumno.listarRelacionados();
                case 9 -> viewAlumno.obtenerAlumno();
                case 10 -> salir = true;
                default -> throw new AssertionError();
            }
        }
    }
}
