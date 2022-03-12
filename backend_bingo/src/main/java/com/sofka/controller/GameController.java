package com.sofka.controller;

import com.sofka.domain.Game;
import com.sofka.service.GameService;
import com.sofka.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    private Response response = new Response();

    /**
     * listar todos los juegos
     */
    @GetMapping(path="/games")
    public Response listar() {
        try{
            response.data = gameService.list();
        }catch (Exception exc){
            response.error = true;
            response.message = exc.getMessage();
            response.status = "ERROR";
        }
        return response;
    }

    /**
     * crear un juego
     */
    @PostMapping(path="/game")
    public ResponseEntity<Game> crear(Game game) {
        log.info("Juego a crear: {}", game);
        gameService.save(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    /**
     * borrar un juego
     */
    @DeleteMapping(path="/game/{id}")
    public ResponseEntity<Game>  borrar(Game game) {
        log.info("Juego a borrar: {}", game);
        gameService.delete(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    /**
     * actualizar un juego
     */
    @PutMapping(path="/game/{id}")
    public ResponseEntity<Game> actualizar(Game game, @PathVariable("id") Long id) {
        log.info("Juego a modificar: {}", game);
        gameService.update(id,game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    /**
     * actualizar campos del juego para indicar ganador
     */
    @PatchMapping(path="/game/winner/{id}")
    public ResponseEntity<Game>  actualizarGanador(Game game, @PathVariable("id") Long id) {
        log.info("Juego a modificar ganador: {}", game);
        gameService.updateWinner(id,game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

}