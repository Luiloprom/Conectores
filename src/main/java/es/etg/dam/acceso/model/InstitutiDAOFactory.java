package es.etg.dam.acceso.model;

import es.etg.dam.acceso.model.db.InstitutoOracleXeDAOImp;
import es.etg.dam.acceso.model.db.InstitutoSQLiteDAOImp;
import es.etg.dam.acceso.model.mock.InstitutoMockDAOImp;

public class InstitutiDAOFactory {

    public static InstitutoDAO obtenerModo(Modo modo) throws Exception {
        return switch (modo) {
            case SQLITE -> new InstitutoSQLiteDAOImp();
            case ORACLE -> new InstitutoOracleXeDAOImp();
            case MOCK -> new InstitutoMockDAOImp();
            default -> new InstitutoMockDAOImp();
        };
    }

}
