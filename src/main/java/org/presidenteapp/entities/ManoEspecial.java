package org.presidenteapp.entities;

import java.io.IOException;
import java.util.List;

public class ManoEspecial extends Mano{
    private Mano ultimaMano;

    public ManoEspecial(List<Jugador> jugadores, Mazo mazo, Mano ultMano) {
        super(jugadores, mazo);
        this.ultimaMano = ultMano;
    }


    public void intercambiarCartas(){
        Jugador presidente = this.ultimaMano.getPresidente();
        Jugador vicepresidente = this.ultimaMano.getVicepresidente();
        Jugador anteultimo = this.ultimaMano.getAnteUltimo();
        Jugador ultimo = this.ultimaMano.getUltimo();

        ultimo.addCartas(presidente.getPeoresCartas(2));
        anteultimo.addCartas(vicepresidente.getPeoresCartas(1));

        vicepresidente.addCartas(anteultimo.getMejoresCartas(1));
        presidente.addCartas(ultimo.getMejoresCartas(2));
    }
}
