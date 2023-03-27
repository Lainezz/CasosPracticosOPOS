package org.practicos.canarias.clases;

public class Butaca {

    // ATRIBUTOS DE CLASE
    private int fila;
    private int asiento;

    // CONSTRUCTORES DE CLASE
    public Butaca(String linea) {
        String[] filaXAsiento = linea.split(":");
        this.fila = Integer.parseInt(filaXAsiento[0]);
        this.asiento = Integer.parseInt(filaXAsiento[1]);
    }

    // GETTERS AND SETTERS
    public Butaca(int fila, int asiento) {
        this.fila = fila;
        this.asiento = asiento;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }


    @Override
    public String toString() {
        return fila+":"+asiento;
    }
}
