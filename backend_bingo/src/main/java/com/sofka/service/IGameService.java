package com.sofka.service;

import java.util.List;
import java.util.Optional;

import com.sofka.domain.Game;

public interface IGameService {

    public List<Game> list();

    public Game save(Game game);

    public Game update(Long id, Game game);

    public void delete(Game game);

    public void updateWinner(Long id, Game game);

    public Optional<Game> findContact(Game game);

}