package org.presidenteapp.controller;

import org.presidenteapp.controller.DTO.Turno;
import org.presidenteapp.controller.DTO.connectDTO;
import org.presidenteapp.controller.DTO.movJugadorDTO;
import org.presidenteapp.entities.Jugador;
import org.presidenteapp.entities.Presidente;
import org.presidenteapp.services.PresidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class PresidenteController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private PresidenteService presidenteService;

    @MessageMapping("/join")
    //@SendTo("/game/{roomId}")
    public ResponseEntity<?> connectRoom(connectDTO joinRoomDTO, SimpMessageHeaderAccessor headerAccessor) throws IOException {
        Jugador j = new Jugador(joinRoomDTO.getNombre());
        headerAccessor.getSessionAttributes().put("username", joinRoomDTO.getNombre());
        System.out.println(headerAccessor.getSessionAttributes().entrySet() + " y la sala: " + joinRoomDTO.getIdSala());

        Turno t = presidenteService.joinToGame(joinRoomDTO.getIdSala(), j);


        simpMessagingTemplate.convertAndSend("/topic/game/" + joinRoomDTO.getIdSala(), t);
        return ResponseEntity.ok(t);
    }

//    @MessageMapping("/ready")
//   //@SendTo("/game/{roomID}")
//    public ResponseEntity<SalaDeJuego> readyToPlay(connectDTO joinRoomDTO) throws IOException {
//        //SalaDeJuego s = presidenteService.ready(joinRoomDTO.getIdSala(), joinRoomDTO.getNombre());
//        simpMessagingTemplate.convertAndSend("/topic/game/" + joinRoomDTO.getIdSala());
//        //return ResponseEntity.ok(s);
//    }

    @MessageMapping("/play")
    //@SendTo("/game/{roomID}")
    public ResponseEntity<?> playTurn(movJugadorDTO movimiento) throws IOException {
        presidenteService.play(movimiento);
        return null;
    }

//    @MessageMapping("/presidente")
//    //@SendTo("/game/{roomID}")
//    public ResponseEntity<?> newGame(movJugadorDTO movimiento) throws IOException {
//        presidenteService.play(movimiento);
//        return null;
//    }

}
