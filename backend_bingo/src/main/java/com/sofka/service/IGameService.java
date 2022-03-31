package com.sofka.service;

import java.util.List;
import java.util.Optional;

import com.sofka.domain.Game;

/**
 * interfaz con metodos abstractos a usar por el servicio de los juegos
 * @version 1.0.0 2022-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

public interface IGameService {

    public List<Game> list();

    public Game save(Game game);

    public Game update(Long id, Game game);

    public void delete(Game game);

    public void updateWinner(Long id, Game game);

    public Optional<Game> findContact(Game game);

}
