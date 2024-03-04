package org.presidenteapp.entities;

public class Carta implements Comparable {
    private int numero;
    private String palo;
    private int orden;

    public Carta(int numero, String palo, int orden) {
        this.numero = numero;
        this.palo = palo;
        this.orden = orden;
    }

    public int getNumero() {
        return numero;
    }

    public String getPalo() {
        return palo;
    }

    public int getOrden() {
        return orden;
    }

    @Override
    public String toString() {
        return numero +" de " + palo;
    }

    @Override
    public int compareTo(Object o) {
        Carta c = (Carta) o;
        if(this.orden > c.getOrden()){
            return 1;
        } else if(this.orden < c.getOrden()){
            return -1;
        }
        return 0;
    }
}
