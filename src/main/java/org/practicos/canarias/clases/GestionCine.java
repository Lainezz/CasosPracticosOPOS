package org.practicos.canarias.clases;

import java.util.Scanner;

public class GestionCine {

    private Cine cine;

    // GETTERS AND SETTERS
    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public GestionCine(Cine cine) {
        this.cine = cine;
    }

    public void gestionarMenu() {
        Butaca b;
        int respuesta;
        /*
        Mostrar menú principal
        1º Haciendo uso de la técnica de "divide y vencerás", aislamos la "pantalla" del menú principal
        en un método para así poder llamarlo cada vez que queramos. Como el método pertenece a esta clase,
        no hace falta inicializar un objeto para llamar al método
         */
        int opcionEscogida = 0;
        cine.leerAsientos("C:\\Users\\diego\\Desktop\\Workspaces\\CasosPracticos_Oposiciones\\src\\main\\resources\\entradasVendidas.txt");
        do {
            try {
                opcionEscogida = menuPrincipal();
                switch (opcionEscogida) {
                    case 1:
                        System.out.println("MOSTRAR ASIENTOS");

                        cine.mostrarAsientos();

                        break;
                    case 2:
                        System.out.println("COMPRAR ENTRADA");

                        b = menuGestionButacas();
                        respuesta = cine.comprarEntrada(b);
                        if(respuesta == 0) menuAsientoIncorrecto(b);
                        else if(respuesta == 1) menuCompraEfectuada(b);
                        else menuErrorInesperado();

                        break;
                    case 3:
                        System.out.print("DEVOLVER ENTRADA\n");

                        b = menuGestionButacas();
                        respuesta = cine.devolverEntrada(b);
                        if(respuesta==0) menuAsientoIncorrecto(b);
                        else if(respuesta==1) menuDevolucionEfectuada(b);
                        else menuErrorInesperado();

                        break;
                    case 4:
                        System.out.print("SALIR\n");

                        cine.escribirAsientos();

                        break;
                    default:
                        System.err.print("OPCION NO SOPORTADA.\n");
                        break;
                }
            } catch (Exception e) {
                menuErrorInesperado();
            }

        } while (opcionEscogida != 4);
    }

    private void menuErrorInesperado() {
        System.err.println("Error inesperado, se recomienda reiniciar la aplicación");
    }

    private void menuCompraEfectuada(Butaca b) {
        System.out.print("\t---------\n");
        System.out.format("%s\n", Cine.nombreCine);
        System.out.format("Fila: %s\n", b.getFila());
        System.out.format("Asiento %s\n", b.getAsiento());
        System.out.print("\t---------\n");
    }

    private void menuDevolucionEfectuada(Butaca b) {
        System.out.print("\t---------\n");
        System.out.format("%s\n", Cine.nombreCine);
        System.out.println("Devolución efectuada");
        System.out.print("\t---------\n");
    }

    private void menuAsientoIncorrecto(Butaca b) {
        System.out.format("Asiento %s:%s no elegible, por favor elija otro", b.getFila(), b.getAsiento());
    }

    private int menuPrincipal() {
        // Mostramos las opciones del menú principal
        System.out.print("\t---------\n");
        System.out.print("1. Mostrar butacas\n");
        System.out.print("2. Comprar entrada\n");
        System.out.print("3. Devolver entrada\n");
        System.out.print("4. Salir\n");
        System.out.print("\t---------\n");

        /*
            1º Leemos lo que el usuario introduce por teclado (para ello utilizamos la clase Scanner)
            2º Instanciamos la clase Scanner para obtener un objeto
            3º Utilizamos el método .nextLine()
         */
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca la opción deseada: ");
        String opcionTeclado = sc.nextLine();
        // Podemos pasar lo que el usuario ha escrito por teclado al formato que nosotros deseemos haciendo uso del casting.
        int opc = Integer.parseInt(opcionTeclado.trim());

        return opc;
    }

    private Butaca menuGestionButacas(){
        System.out.print("\t---------\n");
        System.out.print("Escoja la fila (0-9)\n");

        Scanner sc = new Scanner(System.in);
        String opcionTeclado = sc.nextLine();
        // Podemos pasar lo que el usuario ha escrito por teclado al formato que nosotros deseemos haciendo uso del casting.
        int fila = Integer.parseInt(opcionTeclado.trim());

        System.out.print("Escoja el asiento (0-14)\n");
        opcionTeclado = sc.nextLine();
        // Podemos pasar lo que el usuario ha escrito por teclado al formato que nosotros deseemos haciendo uso del casting.
        int asiento = Integer.parseInt(opcionTeclado.trim());

        return new Butaca(fila, asiento);
    }
}
