package com.sofka.service;

import com.sofka.domain.GameBallot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * interfaz con metodos abstractos a usar por el servicio de las balotas
 * @version 1.0.0 2022-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

public interface IGameBallotService {

    public List<GameBallot> list();

    public GameBallot save(GameBallot gameBallot);

    public GameBallot update(Long id, GameBallot gameBallot);

    public void delete(GameBallot gameBallot);

    public ArrayList<Integer> ballotOut(Long id);

    public Optional<GameBallot> findContact(GameBallot gameBallot);

}
