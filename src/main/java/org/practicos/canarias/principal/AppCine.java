package org.practicos.canarias.principal;

import org.practicos.canarias.clases.Cine;
import org.practicos.canarias.clases.GestionCine;

public class AppCine {
    public static void main(String[] args) {
        GestionCine gestor = new GestionCine(new Cine());
        gestor.gestionarMenu();
    }
}
