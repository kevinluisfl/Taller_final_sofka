package com.sofka.service;

import com.sofka.domain.Game;
import com.sofka.domain.GamePlayer;

import java.util.List;
import java.util.Optional;

public interface IGamePlayerService {

    public List<GamePlayer> list();

    public GamePlayer save(GamePlayer gamePlayer);

    public GamePlayer update(Long id, GamePlayer gamePlayer);

    public void delete(GamePlayer gamePlayer);

    public void updateLoser(Long id, GamePlayer gamePlayer);

    public Optional<GamePlayer> findContact(GamePlayer gamePlayer);

}
