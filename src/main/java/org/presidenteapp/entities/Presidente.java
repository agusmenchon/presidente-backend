package org.presidenteapp.entities;

import lombok.Getter;
import lombok.Setter;
import org.presidenteapp.controller.DTO.Turno;
import org.presidenteapp.controller.DTO.movJugadorDTO;

import java.util.ArrayList;
import java.util.List;

import static org.presidenteapp.entities.GameStatus.FINISHED;
import static org.presidenteapp.entities.GameStatus.GAME_INPROGRESS;


public class Presidente {

    @Getter
    @Setter
    private String id;

    @Getter
    private List<Jugador> jugadores;
    @Getter
    private Mazo mazo;
    @Getter
    private List<Mano> manos;
    @Getter
    @Setter
    private Mano manoActual;
    @Getter
    @Setter
    private boolean finalizo;
    @Setter
    @Getter
    private Jugador ganador;
    @Setter
    @Getter
    private Jugador perdedor;
    @Getter
    private int contador = 0;
    private boolean primero = true;
    @Getter
    @Setter
    private GameStatus status;

    public void setContador() {
        this.contador++;
    }

    public Presidente() {
        this.jugadores = new ArrayList<>();
        this.manos = new ArrayList<>();
        this.finalizo = false;
        this.ganador = null;
        this.perdedor = null;
    }

    public void addPlayer(Jugador j){
        this.jugadores.add(j);
    }

    public int getCantManosJugadas() {
        return manos.size();
    }

    public Turno playGame(){
        this.mazo = new Mazo(this.jugadores.size());
        return this.getTurno();
    }

    public Turno getTurno(){
        if((this.getGanador() == null) && (this.getPerdedor() == null)){
            Turno t = null;
            Mano m = null;
            if(manoActual==null){
                this.contador++;
                if(contador%2==0){
                    m = new ManoEspecial(this.getJugadores(), this.getMazo(), this.manos.get(this.manos.size()-1));
                } else {
                    m = new Mano(this.jugadores, this.mazo);
                }
                m.setStatus(GAME_INPROGRESS);
                m.darCartas();
                m.intercambiarCartas();
                this.manoActual = m;
            }
            t = manoActual.getTurno();
            t.setIdMano(this.manos.size()-1);
            return t;
        }
        //TODO error el ganador o perdedor es tal: ...
        return null;
    }

    public void jugarTurno(movJugadorDTO turno){
        this.manoActual.jugarTurno(turno);
        if(turno.getJugador().getPuntos() == 10){
            this.finalizo = true;
            System.out.println("El ganador es " + turno.getJugador().getNombre());
        }
        if(manoActual.getUltimo()!=null){
            manoActual.setStatus(FINISHED);
            this.manos.add(manoActual);
            manoActual=null;
        }
    }


    //LOGICA
//    public void jugar() throws IOException {
//        int contador = 1;
//        Mano m;
//        while((this.ganador == null) || (this.perdedor == null)) {
//            if(contador%2==0){
//                //JUGAR MANO ESPECIAL
//                m = new ManoEspecial(this.getJugadores(), this.getMazo(), this.manos.get(this.manos.size()-1));
//            } else {
//                // JUGAR MANO
//                m = new Mano(this.getJugadores(), this.getMazo());
//            }
//            //m.empezarMano();
//
//
//            manos.add(m);
//            contador++;
//            for(Jugador j : this.jugadores){
//                if(j.getPuntos() == 10) {
//                    this.ganador = j;
//                    this.finalizo = true;
//                    System.out.println("El ganador es " + j.getNombre());
//                    return;
//                } else if (j.getPuntos() == -10) {
//                    this.perdedor = j;
//                    this.finalizo = true;
//                    System.out.println("El perdedor es " + j.getNombre());
//                    return;
//                }
//            }
//        }
//    }

}
