package org.presidenteapp.services;

import lombok.Getter;
import org.presidenteapp.controller.DTO.JugadorId;
import org.presidenteapp.controller.DTO.movJugadorDTO;
import org.presidenteapp.entities.Jugador;
import org.presidenteapp.entities.Presidente;
import org.presidenteapp.controller.DTO.Turno;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

import static org.presidenteapp.entities.GameStatus.*;

@Service
public class PresidenteService {

//    private SimpMessagingTemplate simpMessagingTemplate;
    @Getter
    private static HashMap<String, Presidente> games;


    public PresidenteService() {
        games= new HashMap<String, Presidente>();
    }
   public Turno joinToGame(String roomId, Jugador jugador) throws IOException {
        Presidente p = games.get(roomId);
        Turno t = null;

        //si la sala no esta creada... se crea y se agrega al jugador
        if(p == null){
            p = new Presidente();
            games.put(roomId, p);
            p.setStatus(LOADING_PLAYERS);
        }
        p.addPlayer(jugador);
        if(p.getJugadores().size() == 6){
            t = p.playGame();
        } else {
            t = new Turno();
            t.setIdGame(roomId);
            t.setStatus(LOADING_PLAYERS);
            t.setJugadores(p.getJugadores());
        }
//        simpMessagingTemplate.convertAndSend("/topic/game/" + roomId, t);
        return t;
    }

    public Integer getIndexJugador(String name, String roomId){
        Presidente p = games.get(roomId);
        Integer index = -1;
        for(int i = 0;i<p.getJugadores().size();i++){
            if(p.getJugadores().get(i).getNombre().equals(name)){
                index = i;
                break;
            }
        }
        System.out.println(index);
        if(index==-1){
            return null;
        }
        return index;
    }


//    public Jugador getPlayer(SalaDeJuego s, String nombre){
//        return s.getJugador(nombre);
//    }


//    public Presidente ready(Integer roomId, String jugador) throws IOException {
//        Presidente p = games.get(roomId);
//        Jugador j =
//        s.readyToPlay(j);
//        return s;
//    }

    public void play(movJugadorDTO jugador) throws IOException {
        Presidente p = this.games.get(jugador.getIdGame());
        //TODO manejo de errores con metodos comprobando si la tirada es OK

        //jugar turno
        p.jugarTurno(jugador);

        //cargo el DTO con el turno siguiente
        Turno turnoSiguiente = p.getTurno();

        //y lo envio a todos los participantes de la sala.
//        simpMessagingTemplate.convertAndSend("/topic/game/" + jugador.getIdGame(), turnoSiguiente);
    }


}
