package es.etg.dam.acceso.controller;

import es.etg.dam.acceso.model.Instituto;
import es.etg.dam.acceso.model.InstitutoFactory;
import es.etg.dam.acceso.model.Modo;
import es.etg.dam.acceso.model.db.alumno.AlumnoDAO;
import es.etg.dam.acceso.view.ViewController;

public class InstitutoController {

    private final ViewController menuInicial;
    private Instituto instituto;

    public InstitutoController(ViewController menuInicial) {
        this.menuInicial = menuInicial;
    }

    public void empezar() throws Exception {
        Modo modo = menuInicial.cargarMenuInicial();
        AlumnoDAO alumnoDAO = InstitutoFactory.obtenerModo(modo);
        instituto = new Instituto(alumnoDAO);
    }

}
