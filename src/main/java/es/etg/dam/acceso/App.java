package es.etg.dam.acceso;

import es.etg.dam.acceso.controller.InstitutoController;

public class App {
    public static void main(String[] args) throws Exception {

        InstitutoController institutoController = new InstitutoController();

        institutoController.empezar();
    }
}