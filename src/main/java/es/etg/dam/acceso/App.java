package es.etg.dam.acceso;

import java.util.List;

import es.etg.dam.acceso.model.Alumno;
import es.etg.dam.acceso.model.InstitutoDAO;
import es.etg.dam.acceso.model.db.InstitutoOracleXeDAOImp;
import es.etg.dam.acceso.model.db.InstitutoSQLiteDAOImp;

public class App {
    public static void main(String[] args) throws Exception {
        InstitutoDAO institutoDAO = new InstitutoSQLiteDAOImp();
        InstitutoOracleXeDAOImp oracle = new InstitutoOracleXeDAOImp();

        System.out.println(oracle.testConexion());

    }

    public static void pintarAlumnos(List<Alumno> alumnos) {
        for (Alumno alumno : alumnos) {
            System.out.println("Nombre : " + alumno.getNombre() + " Apellido : " + alumno.getApellido() + " Edad : "
                    + alumno.getEdad());
        }
    }
}