package es.etg.dam.acceso.view;

import javax.swing.JOptionPane;

import es.etg.dam.acceso.model.Modo;

public class ViewController {

    public Modo cargarMenuInicial() {
        int respuesta = Integer
                .parseInt(JOptionPane.showInputDialog("Elije una opcion : \n 1 - Mock \n 2 - SQLITE \n 3 - Oracle "));
        return switch (respuesta) {
            case 1 -> Modo.MOCK;
            case 2 -> Modo.SQLITE;
            case 3 -> Modo.ORACLE;
            default -> Modo.MOCK;
        };
    }
}
