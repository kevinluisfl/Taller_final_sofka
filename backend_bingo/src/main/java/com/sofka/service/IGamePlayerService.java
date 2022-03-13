package com.sofka.service;

import com.sofka.domain.Game;
import com.sofka.domain.GamePlayer;

import java.util.List;
import java.util.Optional;

/**
 * interfaz con metodos abstractos a usar por el servicio de los jugadores
 * @version 1.0.0 2002-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

public interface IGamePlayerService {

    public List<GamePlayer> list();

    public GamePlayer save(GamePlayer gamePlayer);

    public GamePlayer update(Long id, GamePlayer gamePlayer);

    public void delete(GamePlayer gamePlayer);

    public void updateLoser(Long id, GamePlayer gamePlayer);

    public Optional<GamePlayer> findContact(GamePlayer gamePlayer);

}
