package com.sofka.controller;

import com.sofka.domain.GameBallot;
import com.sofka.service.GameBallotService;
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
public class GameBallotController {
    @Autowired
    private GameBallotService gameBallotService;

    private Response response = new Response();

    /**
     * listar todos las balotas de juegos
     */
    @GetMapping(path="/gamesballots")
    public Response listar() {
        try{
            response.data = gameBallotService.list();
        }catch (Exception exc){
            response.error = true;
            response.message = exc.getMessage();
            response.status = "ERROR";
        }
        return response;
    }

    /**
     * asignar una balota a un juego
     */
    @PostMapping(path="/gameballot")
    public ResponseEntity<GameBallot> crear(GameBallot gameBallot) {
        log.info("balota de Juego a crear: {}", gameBallot);
        gameBallotService.save(gameBallot);
        return new ResponseEntity<>(gameBallot, HttpStatus.CREATED);
    }

    /**
     * borrar una balota de un juego
     */
    @DeleteMapping(path="/gameballot/{id}")
    public ResponseEntity<GameBallot>  borrar(GameBallot gameBallot) {
        log.info("balota de Juego a borrar: {}", gameBallot);
        gameBallotService.delete(gameBallot);
        return new ResponseEntity<>(gameBallot, HttpStatus.OK);
    }

    /**
     * actualizar un jugador de un juego
     */
    @PutMapping(path="/gameballot/{id}")
    public ResponseEntity<GameBallot> actualizar(GameBallot gameBallot, @PathVariable("id") Long id) {
        log.info("balota de juego a modificar: {}", gameBallot);
        gameBallotService.update(id,gameBallot);
        return new ResponseEntity<>(gameBallot, HttpStatus.OK);
    }

}
