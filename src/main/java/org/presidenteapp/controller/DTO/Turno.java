package org.presidenteapp.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.presidenteapp.entities.Carta;
import org.presidenteapp.entities.GameStatus;
import org.presidenteapp.entities.Jugador;

import java.util.List;

@Data
@AllArgsConstructor
public class Turno {

    private String idGame;
    private Integer idMano;
    private List<Jugador> jugadores;
    private Integer indexTurnoJugador;
    private List<Carta> mazo;
    private Integer cantCartas;
    private Boolean escalera;
    private Boolean primero;
    private GameStatus status;

    public Turno(){
        this.idMano = null;
        this.jugadores = null;
        this.indexTurnoJugador = null;
        this.mazo = null;
        this.cantCartas = null;
        this.escalera = null;
        this.primero = null;
        this.idGame = null;
        this.status = null;
    }
}
