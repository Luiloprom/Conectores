package es.etg.dam.acceso.model;

import es.etg.dam.acceso.model.db.alumno.AlumnoDAO;
import es.etg.dam.acceso.model.db.alumno.AlumnoOracleXeDAOImp;
import es.etg.dam.acceso.model.db.alumno.AlumnoSQLiteDAOImp;
import es.etg.dam.acceso.model.mock.AlumnoMockDAOImp;

public class InstitutoFactory {

    public static AlumnoDAO obtenerModo(Modo modo) throws Exception {
        return switch (modo) {
            case SQLITE -> new AlumnoSQLiteDAOImp();
            case ORACLE -> new AlumnoOracleXeDAOImp();
            case MOCK -> new AlumnoMockDAOImp();
            default -> new AlumnoMockDAOImp();
        };
    }

}
