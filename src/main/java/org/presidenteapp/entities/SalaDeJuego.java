//package org.presidenteapp.entities;
//
//import jakarta.websocket.Session;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.presidenteapp.controller.DTO.GameInfoDTO;
//import org.presidenteapp.controller.DTO.movJugadorDTO;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//
//import static org.presidenteapp.entities.GameStatus.IN_PROGRESS;
//
//public class SalaDeJuego {
//    //private Integer idSala;
//    private HashMap<Jugador, Boolean> jugadores;
//    private Presidente juego;
//    private GameStatus status;
//
//    public GameStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(GameStatus status) {
//        this.status = status;
//    }
//
//    public SalaDeJuego() {
//        //this.idSala = nroSala;
//        this.jugadores = new HashMap<>();
//    }
//
////    public Integer getIdSala() {
////        return idSala;
////    }
//
//    public Jugador getJugador(String nombre){
//        for(Jugador j : this.jugadores.keySet()){
//            if(j.getNombre() == nombre){
//                return j;
//            }
//
//        }
//        return null;
//    }
//
//    public HashMap<Jugador, Boolean> getJugadores() {
//        return jugadores;
//    }
//
//    public Presidente getJuego() {
//        return juego;
//    }
//
//    public Presidente empezarJuego() throws IOException {
//        if(this.jugadores.size() > 4){
//            //si hay algun jugador que no puso ready.
//            for(boolean b : this.jugadores.values()){
//                if(!b){
//                    return null;
//                }
//            }
//            if(this.juego==null){
//                this.setStatus(IN_PROGRESS);
//                this.juego = new Presidente((List<Jugador>) this.jugadores.keySet());
//                this.getTurno();
//                return juego;
//            }
//        }
//        return null;
//    }
//
//    public Turno getTurno() throws IOException {
//        return this.juego.getTurno();
//    }
//    public void entrarASala(Jugador j){
//        this.jugadores.put(j, false);
//    }
//
//    public void readyToPlay(Jugador j){
//        this.jugadores.put(j, true);
//    }
//
//    public void mover(movJugadorDTO turno){
//        this.juego.jugarTurno(turno);
//    }
//
//}

/*
Jugadores se unen a la sala y ponen ready
Se manda la partida y se llama a empezarJuego(). Crea una instancia de Juego y llama a getTurno().
En getTurno se verifica si hay que crear una nueva MANO.
Sino,


*
* */