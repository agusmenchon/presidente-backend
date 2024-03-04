package org.presidenteapp.entities;

import lombok.Getter;
import lombok.Setter;
import org.presidenteapp.controller.DTO.Turno;
import org.presidenteapp.controller.DTO.movJugadorDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mano {
    //private Integer idMano;
    private List<Jugador> jugadores;
    private Mazo mazo;
    private Map<Carta, Jugador> cartasTiradas;
    private boolean finalizo;

    //private HashMap<Jugador, Integer> posicion;
    @Getter
    private Jugador presidente = null;
    @Getter
    private Jugador vicepresidente = null;
    private List<Jugador> pueblos;
    @Getter
    private Jugador anteUltimo = null;
    @Getter
    private Jugador ultimo = null;
    private static final int _PRIMERO = 2;
    private static final int _SEGUNDO = 1;
    private static final int _PUEBLO = 0;
    private static final int _ANTEULTIMO = -1;
    private static final int _ULTIMO = -2;

    //atributos para LOGICA DE TURNO
    private int indexJugador;
    private boolean escalera = false;
    private Integer cantCartasRonda = null;
    private Jugador ultJugadorEnTirar = null;
    private boolean primeroRonda;
    private int ult = 0;
    private int anteult = 0;
    private int antepenult = 0;

    @Getter
    @Setter
    private GameStatus status;



    public Jugador getJugador(Jugador jugador) {
        for (Jugador j : jugadores) {
            if (jugador.equals(j)) {
                return j;
            }
        }
        return null;
    }


    public Mano(List<Jugador> jugadores, Mazo mazo) {
        this.jugadores = jugadores;
        this.mazo = mazo;
        this.cartasTiradas = new HashMap<>();
        this.finalizo = false;
    }

    private int cantpueblos() {
        if (this.jugadores.size() == 6) {
            return 2;
        }
        return 1;
    }

    public Turno getTurno() {
        Turno t = new Turno();

        t.setEscalera(this.escalera);
        t.setJugadores(this.jugadores);
        t.setIndexTurnoJugador(this.indexJugador);
        t.setMazo(this.mazo.getMazo());
        t.setCantCartas(this.cantCartasRonda);
        t.setPrimero(this.primeroRonda);
        t.setStatus(GameStatus.GAME_INPROGRESS);

        return t;
    }

    public void darCartas() {
        mazo.mezclarMazo();
        while (!mazo.getMazo().isEmpty()) {
            int index = 0;
            for (Jugador j : jugadores) {
                Carta c = mazo.getUltima();
                this.mazo.eliminarUltima();
                if (c.getOrden() == 1) {
                    this.indexJugador = index;
                }
                j.addCarta(c);
                index++;
            }
        }
        for (Jugador j : this.jugadores) {
            System.out.println("Jugador: " + j.getNombre() + ": " + j.getCartas());
        }
        System.out.println("Arranca la mano: " + this.jugadores.get(indexJugador).getNombre());
    }

    public Turno jugarTurno(movJugadorDTO turno) {
        if (ultJugadorEnTirar != null && !this.finalizo) {
            if (ultJugadorEnTirar.equals(turno.getJugador())) {
                this.mazo.getMazo().clear();
                //indexJugadorMano = i;
                ult = 0;
                anteult = 0;
                antepenult = 0;
                escalera = false;
                primeroRonda = true;
                this.cantCartasRonda = null;
            }
        }

        if (this.jugadores.get(indexJugador).equals(turno.getJugador())) {
            if (this.cantCartasRonda == turno.getCartasATirar().size()) {
                for(Carta c : turno.getCartasATirar()){
                    System.out.println(c.toString());
                }
                this.primeroRonda = false;

                if (!turno.getCartasATirar().isEmpty()) {
                    cantCartasRonda = turno.getCartasATirar().size();
                    for (Carta c : turno.getCartasATirar()) {
                        this.mazo.getMazo().add(c);
                        turno.getJugador().getCartas().remove(c);
                        this.cartasTiradas.put(c, turno.getJugador());
                    }
                    ultJugadorEnTirar = turno.getJugador();

                    if (ultJugadorEnTirar.getCartas().isEmpty()) {
                        this.jugadores.remove(turno.getJugador());
                        indexJugador--;

                        if (this.presidente == null) {
                            this.presidente = ultJugadorEnTirar;
                            this.presidente.sumarPuntos(_PRIMERO);
                        } else if (this.vicepresidente == null) {
                            this.vicepresidente = ultJugadorEnTirar;
                            this.vicepresidente.sumarPuntos(_SEGUNDO);
                        } else if (this.pueblos.size() < this.cantpueblos()) {
                            this.pueblos.add(ultJugadorEnTirar);
                            ultJugadorEnTirar.sumarPuntos(_PUEBLO);
                        } else if (this.anteUltimo == null) {
                            this.anteUltimo = ultJugadorEnTirar;
                            ultJugadorEnTirar.sumarPuntos(_ANTEULTIMO);
                        } else if (this.ultimo == null) {
                            this.ultimo = ultJugadorEnTirar;
                            ultJugadorEnTirar.sumarPuntos(_ULTIMO);
                            this.finalizo = true;
                            return this.getTurno();
                        }
                    }

                    if (!this.escalera) {
                        antepenult = anteult;
                        anteult = ult;
                        ult = turno.getCartasATirar().get(0).getNumero();
                        if (this.esEscalera()) {
                            escalera = true;
                        }
                    }
                }
            }
            if(indexJugador==this.jugadores.size()-1 && !this.finalizo){
                indexJugador=0;
            } else {
                indexJugador++;
            }
        }
        return this.getTurno();
    }

//        public void start () throws IOException {
//            int i = this.indexJugadorMano; //i es jugadores(i) donde arranca la mano
//            int ult = 0;
//            int anteult = 0;
//            int antepenult = 0;
//            boolean escalera = false;
//            Jugador ultJugador = null;
//            boolean primero = true;
//            Integer cantCartas = null;
//
//            for (; i < this.jugadores.size(); i++) {
//
//                if (ultJugador == this.jugadores.get(i)) {
//                    this.mazo.getMazo().clear();
//                    indexJugadorMano = i;
//                    ult = 0;
//                    anteult = 0;
//                    antepenult = 0;
//                    escalera = false;
//                    primero = true;
//                    cantCartas = null;
//                    System.out.println("\nEl jugador " + ultJugador.getNombre() + " a ganado la ronda y le toca jugar a el nuevamente");
//                }
//
//                List<Carta> cartas = this.jugadores.get(i).jugarCartas(this.mazo, escalera, primero, cantCartas);
//
//                primero = false;
//
//                if (!cartas.isEmpty()) {
//                    cantCartas = cartas.size();
//                    for (Carta c : cartas) {
//                        this.mazo.getMazo().add(c);
//                        this.jugadores.get(i).getCartas().remove(c);
//                        this.cartasTiradas.put(c, this.jugadores.get(i));
//                    }
//                    ultJugador = this.jugadores.get(i);
//
////                TODO pasar de ronda automaticamente cuando se tira la carta mas alta
////                if(c.getOrden() == this.mayorOrden){
////                    i = i-1;
////                    this.mayorOrden--;
////                }
//
//                    //ASIGNAR PUNTAJES DESPUES DE LA RONDA
//                    if (ultJugador.getCartas().isEmpty()) {
//                        this.jugadores.remove(i);
//                        i--;
//
//                        if (this.presidente == null) {
//                            this.presidente = ultJugador;
//                            this.presidente.sumarPuntos(_PRIMERO);
//                        } else if (this.vicepresidente == null) {
//                            this.vicepresidente = ultJugador;
//                            this.vicepresidente.sumarPuntos(_SEGUNDO);
//                        } else if (this.pueblos.size() < this.cantpueblos()) {
//                            this.pueblos.add(ultJugador);
//                            ultJugador.sumarPuntos(_PUEBLO);
//                        } else if (this.anteUltimo == null) {
//                            this.anteUltimo = ultJugador;
//                            ultJugador.sumarPuntos(_ANTEULTIMO);
//                        } else if (this.ultimo == null) {
//                            this.ultimo = ultJugador;
//                            ultJugador.sumarPuntos(_ULTIMO);
//                            this.finalizo = true;
//                            return;
//                        }
//                    }
//
//                    //LOGICA RONDA ESCALERA
//                    if (!escalera) {
//                        antepenult = anteult;
//                        anteult = ult;
//                        ult = cartas.get(0).getNumero();
//                        boolean sonConsecutivos = Math.abs(anteult - antepenult) == 1 && Math.abs(ult - anteult) == 1;
//                        if (sonConsecutivos) {
//                            escalera = true;
//                        }
//                    }
//                }
//
//                this.mazo.imprimirMazo();
//                if ((i == this.jugadores.size() - 1) && (!this.finalizo)) {
//                    i = -1;
//                }
//
//            }
//        }

        public boolean esEscalera () {
            boolean sonConsecutivos = Math.abs(anteult - antepenult) == 1 && Math.abs(ult - anteult) == 1;
            if (sonConsecutivos) {
                this.escalera = true;
            }
            return false;
        }

    public void intercambiarCartas() {
    }
}

