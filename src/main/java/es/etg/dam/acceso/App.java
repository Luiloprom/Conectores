package es.etg.dam.acceso;

import es.etg.dam.acceso.controller.InstitutoController;
import es.etg.dam.acceso.view.ViewController;

public class App {
    public static void main(String[] args) throws Exception {
        ViewController viewController = new ViewController();

        InstitutoController institutoController = new InstitutoController(viewController);

        institutoController.empezar();

    }

}