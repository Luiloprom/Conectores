package es.etg.dam.acceso.model;

import es.etg.dam.acceso.model.db.InstitutoSQLiteDAOImp;
import es.etg.dam.acceso.model.mock.InstitutoMockDAOImp;

public class InstitutiDAOFactory {

    public static InstitutoDAO obtenerModo(Modo modo) throws Exception {
        if (modo == Modo.SQLITE) {
            return new InstitutoSQLiteDAOImp();
        } else {
            return new InstitutoMockDAOImp();
        }
    }

}
