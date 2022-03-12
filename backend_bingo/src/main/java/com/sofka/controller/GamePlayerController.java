package com.sofka.controller;

import com.sofka.domain.GamePlayer;
import com.sofka.service.GamePlayerService;
import com.sofka.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GamePlayerController {
    @Autowired
    private GamePlayerService gamePlayerService;

    private Response response = new Response();

    /**
     * listar todos los jugadores de juegos
     */
    @GetMapping(path="/gamesplayers")
    public Response listar() {
        try{
            response.data = gamePlayerService.list();
        }catch (Exception exc){
            response.error = true;
            response.message = exc.getMessage();
            response.status = "ERROR";
        }
        return response;
    }

    /**
     * asignar un jugador a un juego
     */
    @PostMapping(path="/gameplayer")
    public ResponseEntity<GamePlayer> crear(GamePlayer gamePlayer) {
        log.info("jugador de Juego a crear: {}", gamePlayer);
        gamePlayerService.save(gamePlayer);
        return new ResponseEntity<>(gamePlayer, HttpStatus.CREATED);
    }

    /**
     * borrar un jugador de un juego
     */
    @DeleteMapping(path="/gameplayer/{id}")
    public ResponseEntity<GamePlayer>  borrar(GamePlayer gamePlayer) {
        log.info("jugador de Juego a borrar: {}", gamePlayer);
        gamePlayerService.delete(gamePlayer);
        return new ResponseEntity<>(gamePlayer, HttpStatus.OK);
    }

    /**
     * actualizar un jugador de un juego
     */
    @PutMapping(path="/gameplayer/{id}")
    public ResponseEntity<GamePlayer> actualizar(GamePlayer gamePlayer, @PathVariable("id") Long id) {
        log.info("jugador de Juego a modificar: {}", gamePlayer);
        gamePlayerService.update(id,gamePlayer);
        return new ResponseEntity<>(gamePlayer, HttpStatus.OK);
    }

}
