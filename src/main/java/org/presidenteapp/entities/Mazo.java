package org.presidenteapp.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    @Getter
    public List<Carta> mazo;
    public Carta ultima;

    public Mazo(int jugadores) {
        this.mazo = new ArrayList<>();
        agregarCarta(jugadores);
        mezclarMazo();
    }

    public Carta getUltima() {
        return this.mazo.get(this.mazo.size()-1);
    }

    public void eliminarUltima(){
        this.mazo.remove(this.getUltima());
    }

    public void mezclarMazo(){
        Collections.shuffle(mazo);
    }

    public void agregarCarta(int cantJugadores){
        if(mazo.isEmpty()) {
            //2
            Carta c2c = new Carta(2, "Copa", 1);
            Carta c2o = new Carta(2, "Oro", 2);
            Carta c2b = new Carta(2, "Basto", 3);
            Carta c2e = new Carta(2, "Espada", 4);
            mazo.add(c2c);
            mazo.add(c2o);
            mazo.add(c2b);
            mazo.add(c2e);


            //3
            Carta c3c = new Carta(3, "Copa", 5);
            Carta c3o = new Carta(3, "Oro", 6);
            Carta c3b = new Carta(3, "Basto", 7);
            Carta c3e = new Carta(3, "Espada", 8);
            mazo.add(c3c);
            mazo.add(c3o);
            mazo.add(c3b);
            mazo.add(c3e);

            //4
            Carta c4c = new Carta(4, "Copa", 9);
            Carta c4o = new Carta(4, "Oro", 10);
            Carta c4b = new Carta(4, "Basto", 11);
            Carta c4e = new Carta(4, "Espada", 12);
            mazo.add(c4c);
            mazo.add(c4o);
            mazo.add(c4b);
            mazo.add(c4e);

            //5
            Carta c5c = new Carta(5, "Copa", 13);
            Carta c5o = new Carta(5, "Oro", 14);
            Carta c5b = new Carta(5, "Basto", 15);
            Carta c5e = new Carta(5, "Espada", 16);
            mazo.add(c5c);
            mazo.add(c5o);
            mazo.add(c5b);
            mazo.add(c5e);

            //6
            Carta c6c = new Carta(6, "Copa", 17);
            Carta c6o = new Carta(6, "Oro", 18);
            Carta c6b = new Carta(6, "Basto", 19);
            Carta c6e = new Carta(6, "Espada", 20);
            mazo.add(c6c);
            mazo.add(c6o);
            mazo.add(c6b);
            mazo.add(c6e);

            //7
            Carta c7c = new Carta(7, "Copa", 21);
            Carta c7o = new Carta(7, "Oro", 22);
            Carta c7b = new Carta(7, "Basto", 23);
            Carta c7e = new Carta(7, "Espada", 24);
            mazo.add(c7c);
            mazo.add(c7o);
            mazo.add(c7b);
            mazo.add(c7e);

            if(cantJugadores > 5) {
                //8
                Carta c8c = new Carta(8, "Copa", 25);
                Carta c8o = new Carta(8, "Oro", 26);
                Carta c8b = new Carta(8, "Basto", 27);
                Carta c8e = new Carta(8, "Espada", 28);
                mazo.add(c8c);
                mazo.add(c8o);
                mazo.add(c8b);
                mazo.add(c8e);

                //9
                Carta c9c = new Carta(9, "Copa", 29);
                Carta c9o = new Carta(9, "Oro", 30);
                Carta c9b = new Carta(9, "Basto", 31);
                Carta c9e = new Carta(9, "Espada", 32);
                mazo.add(c9c);
                mazo.add(c9o);
                mazo.add(c9b);
                mazo.add(c9e);

            }

            //10
            Carta c10c = new Carta(10, "Copa", 33);
            Carta c10o = new Carta(10, "Oro", 34);
            Carta c10b = new Carta(10, "Basto", 35);
            Carta c10e = new Carta(10, "Espada", 36);
            mazo.add(c10c);
            mazo.add(c10o);
            mazo.add(c10b);
            mazo.add(c10e);

            //11
            Carta c11c = new Carta(11, "Copa", 37);
            Carta c11o = new Carta(11, "Oro", 38);
            Carta c11b = new Carta(11, "Basto", 39);
            Carta c11e = new Carta(11, "Espada", 40);
            mazo.add(c11c);
            mazo.add(c11o);
            mazo.add(c11b);
            mazo.add(c11e);

            //12
            Carta c12c = new Carta(12, "Copa", 41);
            Carta c12o = new Carta(12, "Oro", 42);
            Carta c12b = new Carta(12, "Basto", 43);
            Carta c12e = new Carta(12, "Espada", 44);
            mazo.add(c12c);
            mazo.add(c12o);
            mazo.add(c12b);
            mazo.add(c12e);

            //1
            Carta c1c = new Carta(1, "Copa", 45);
            Carta c1o = new Carta(1, "Oro", 46);
            Carta c1b = new Carta(1, "Basto", 47);
            Carta c1e = new Carta(1, "Espada", 48);
            mazo.add(c1c);
            mazo.add(c1o);
            mazo.add(c1b);
            mazo.add(c1e);
        }
    }

    public void imprimirMazo(){
        System.out.print("Mazo: ");
        for(Carta c : mazo){
            System.out.print(c.toString() + ",");
        }
        System.out.println("");
    }

    public List<Carta> getMazo() {
        return mazo;
    }
}
