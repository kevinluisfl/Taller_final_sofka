package com.sofka.controller;

import com.sofka.domain.Game;
import com.sofka.domain.GamePlayer;
import com.sofka.service.GameBallotService;
import com.sofka.service.GamePlayerService;
import com.sofka.service.GameService;
import com.sofka.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * clase para el control de la logica del juego
 * @version 1.0.0 2022-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

@Slf4j
@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @Autowired
    private GamePlayerService gamePlayerService;

    @Autowired
    private GameBallotService gameBallotService;

    private Response response = new Response();

    Game game = new Game();

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
    /**
     * validar ganador
     */
    @PostMapping(path="/game/bingo/{id}")
    public Response ganar(@RequestBody GamePlayer gamePlayer, @PathVariable("id") Long id) {
        log.info("player: {}", gamePlayer.getPlayer());
        log.info("cardboard: {}", gamePlayer.getCardBoard());
        log.info("cardcheck: {}", gamePlayer.getCardcheck());
        gamePlayer.setGameId(id);

        ////logica revisar ganador
        ArrayList<Integer> cardcheck = gamePlayer.getCardcheck();
        ArrayList<Integer> ballotout;
        //ballotout = gameBallotService.ballotOut(game.getId());
        ballotout = gameBallotService.ballotOut(id);
        ballotout.add(0);
        int lines[][] = { {0,1,2,3,4},{5,6,7,8,9},{10,11,12,13,14},{15,16,17,18,19},{20,21,22,23,24},
                        {0,5,10,15,20},{1,6,11,16,21},{2,7,12,17,22},{3,8,13,18,23},{4,9,14,19,24},
                        {0,6,12,18,24},{4,8,12,16,20},{0,4,12,20,24}};

        log.info("balotas anunciadas: {}", ballotout);
        ///de 0 a 4 horizontal, 5 a 9 verticales, 10 a 11 diagonales, 12 4 esquina
        boolean gano = false;
        String lineaWin = "";
        for(int i = 0; i < lines.length; i++){
            int a = lines[i][0];
            int b = lines[i][1];
            int c = lines[i][2];
            int d = lines[i][3];
            int e = lines[i][4];

            if(ballotout.contains(cardcheck.get(a)) && ballotout.contains(cardcheck.get(b))
                    && ballotout.contains(cardcheck.get(c)) && ballotout.contains(cardcheck.get(d))
                    && ballotout.contains(cardcheck.get(e))){
                log.info("::::::::::::::::::::::::::::::");
                log.info("a: {}", cardcheck.get(a));
                log.info("b: {}", cardcheck.get(b));
                log.info("c: {}", cardcheck.get(c));
                log.info("d: {}", cardcheck.get(d));
                log.info("e: {}", cardcheck.get(e));
                log.info("::::::::::::::::::::::::::::::");
                gano = true;
                if(i < 5){
                    lineaWin="Horizontal "+(i+1);
                }else if(i < 10){
                    lineaWin="Vertical "+(i-4);
                }else if(i < 12){
                    lineaWin="Diagonal";
                }else{
                    lineaWin="4 Esquinas";
                }
            }
        }

        if(gano){
            log.info("GANO!, actualizar ganador de juego, figura: {}", lineaWin);
            game.setId(id);
            game.setGameWinner(gamePlayer.getPlayer());
            game.setCardBoard(gamePlayer.getCardBoard());
            gameService.updateWinner(id,game);
        }else{
            log.info("PERDISTE!, descaificar jugador, : {}");
            gamePlayerService.updateLoser(id,gamePlayer);
        }

        response.dataGame.removeAll(response.dataGame);
        response.dataGame.add(gano);
        response.dataGame.add(lineaWin);
        response.dataGame.add(gamePlayer);
        ///fin logica revisar ganadar
        return response;
    }

}
