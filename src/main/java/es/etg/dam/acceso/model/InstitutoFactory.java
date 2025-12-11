package es.etg.dam.acceso.model;

import es.etg.dam.acceso.dao.AlumnoDAO;
import es.etg.dam.acceso.dao.ProfesorDAO;
import es.etg.dam.acceso.dao.oracle.AlumnoOracleXeDAOImp;
import es.etg.dam.acceso.dao.oracle.ProfesorOracleXeDAOImp;
import es.etg.dam.acceso.dao.sqlite.AlumnoSQLiteDAOImp;
import es.etg.dam.acceso.dao.sqlite.ProfesorSQLiteDAOImp;
import es.etg.dam.acceso.model.mock.AlumnoMockDAOImp;
import es.etg.dam.acceso.model.mock.ProfesorMockDAOImp;

public class InstitutoFactory {

    public static AlumnoDAO obtenerAlumnoDAO(Modo modo) throws Exception {
        return switch (modo) {
            case SQLITE -> new AlumnoSQLiteDAOImp();
            case ORACLE -> new AlumnoOracleXeDAOImp();
            case MOCK -> new AlumnoMockDAOImp();
            default -> new AlumnoMockDAOImp();
        };
    }

    public static ProfesorDAO obtenerProfesorDAO(Modo modo) throws Exception {
        return switch (modo) {
            case SQLITE -> new ProfesorSQLiteDAOImp();
            case ORACLE -> new ProfesorOracleXeDAOImp();
            case MOCK -> new ProfesorMockDAOImp();
            default -> new ProfesorMockDAOImp();
        };
    }

}
