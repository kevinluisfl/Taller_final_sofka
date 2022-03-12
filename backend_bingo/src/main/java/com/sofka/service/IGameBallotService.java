package com.sofka.service;

import com.sofka.domain.GameBallot;

import java.util.List;
import java.util.Optional;

public interface IGameBallotService {

    public List<GameBallot> list();

    public GameBallot save(GameBallot gameBallot);

    public GameBallot update(Long id, GameBallot gameBallot);

    public void delete(GameBallot gameBallot);

    public Optional<GameBallot> findContact(GameBallot gameBallot);

}
