package org.presidenteapp.controller;

import org.presidenteapp.controller.DTO.JugadorId;
import org.presidenteapp.controller.DTO.connectDTO;
import org.presidenteapp.services.PresidenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/id")
public class GameIdController {

    @Autowired
    private PresidenteService presidenteService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user")
    //http://localhost:3000/id/user?idSala=123&nombre=agus
    public ResponseEntity<?> getId(@RequestParam String idSala, @RequestParam String nombre) {
        System.out.println(idSala + " " + nombre);
        Integer i = this.presidenteService.getIndexJugador(nombre, idSala);
        return ResponseEntity.status(HttpStatus.OK).body(i);
    }


}
