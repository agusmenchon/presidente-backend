package org.presidenteapp.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.presidenteapp.entities.Carta;
import org.presidenteapp.entities.Jugador;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class movJugadorDTO {

    private String idGame;
    private Jugador jugador;
    private ArrayList<Carta> cartasATirar;

}
