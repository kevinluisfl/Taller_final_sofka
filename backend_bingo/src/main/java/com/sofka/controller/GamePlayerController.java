package com.sofka.controller;

import com.sofka.domain.Game;
import com.sofka.domain.GamePlayer;
import com.sofka.service.GamePlayerService;
import com.sofka.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public Response crear(GamePlayer gamePlayer) {

        //logica pre creacion
        String codigoCarton = gamePlayer.getGameId()+gamePlayer.getPlayer();
        gamePlayer.setCardBoard(codigoCarton);

        ArrayList<Integer> cardPlayer = new ArrayList<>();
        ArrayList<Integer> cardPlayerCheck = new ArrayList<>();

        int col = 1;
        int max, min;

        for(int i = 0; i < 25; i++){
            if(col==1){
                min =1;
                max=15;
                col +=1;
            }else if(col==2){
                min =16;
                max=30;
                col +=1;
            }else if(col==3){
                min =31;
                max=45;
                col +=1;
            }else if(col==4){
                min =46;
                max=60;
                col +=1;
            }else{
                min =61;
                max=75;
                col = 1;
            }
            int numero = numeroAleatorio(min, max);
            if(i == 12){
                cardPlayer.add(0);
                cardPlayerCheck.add(0);
            }else{
                if (cardPlayer.contains(numero)) {
                    i--;
                    col-=1;
                } else {
                    cardPlayer.add(numero);
                    cardPlayerCheck.add(null);
                }
            }
        }
        log.info("jugador de Juego a crear: {}", gamePlayer);
        log.info("valores carton: {}",cardPlayer);
        log.info("valores carton: {}",cardPlayerCheck);
        //fin logica precreacion

        /**
         * guardando información en la database
         */
        gamePlayerService.save(gamePlayer);
        /**
         * guardando datos a enviar al front
         */
        response.dataGame.add(gamePlayer);
        response.dataGame.add(cardPlayer);
        response.dataGame.add(cardPlayerCheck);
        return response;
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

    /**
     * actualizar campos para descalificar jugador
     */
    @PatchMapping(path="/gameplayer/disqualified/{id}")
    public ResponseEntity<GamePlayer>  actualizarGanador(GamePlayer gamePlayer, @PathVariable("id") Long id) {
        log.info("Juego a modificar ganador: {}", gamePlayer);
        gamePlayerService.updateLoser(id,gamePlayer);
        return new ResponseEntity<>(gamePlayer, HttpStatus.OK);
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
