package com.sofka.controller;

import com.sofka.domain.GameBallot;
import com.sofka.service.GameBallotService;
import com.sofka.util.Response;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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
    public Response crear(GameBallot gameBallot) {
        log.info("balota de Juego a crear: {}", gameBallot);

        ///logica anunciar balota
        ArrayList<Integer> ballotout = new ArrayList<>();
        ArrayList<Integer> ballots = new ArrayList<>();
        ballotout = gameBallotService.ballotOut(gameBallot.getGameId());
        for(int i = 1; i <= 75; i++){
            ballots.add(i);
        }

        ballotout.stream().forEach(b->{
            ballots.remove(ballots.indexOf(b));
        });

        //int ind;
       // for(int i =0;i < ballotout.size(); i++){
       //     ind = ballots.indexOf(ballotout.get(i));
       //     ballots.remove(ind);
       // }
        int indiceAleatorio = numeroAleatorio(0, ballots.size() - 1);
        int newBallot = ballots.get(indiceAleatorio);
        gameBallot.setBallot((long) newBallot);
        ballotout.add(newBallot);

        String letter = "";
        if(newBallot < 16){
            letter="B";
        }else if(newBallot < 31){
            letter="I";
        }else if(newBallot < 46){
            letter="N";
        }else if(newBallot < 61){
            letter="G";
        }else{
            letter="O";
        }

        log.info("balotas anunciadas: {}", ballotout);
        log.info("balotas total: {}", ballots);
        log.info("NUEVA balota : {}",letter + newBallot);
        ///fin logica anunciar balota

        /**
         * guardando información en la database
         */
        gameBallotService.save(gameBallot);
        /**
         * guardando datos a enviar al front
         */
        response.dataGame.removeAll(response.dataGame);
        response.dataGame.add(gameBallot);
        response.dataGame.add(ballotout);
        response.dataGame.add(letter);
        response.dataGame.add(newBallot);
        return response;
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

    /**
     * metodo para calcular un numero entero aleatorio entre un rango
     * @param min valor más pequeño que pueda salir
     * @param max valor más grande que pueda salir
     * @return
     */
    public static int numeroAleatorio(int min, int max) {
        return (int) (Math.random() *(max-min+1) + min);
    }
}
