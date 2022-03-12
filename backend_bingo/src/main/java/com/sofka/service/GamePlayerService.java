package com.sofka.service;

import com.sofka.dao.GamePlayerDao;
import com.sofka.domain.GamePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GamePlayerService implements IGamePlayerService {

    /**
     * metodos implementados de la interfaz
     */
    @Autowired
    private GamePlayerDao gamePlayerDao;

    @Override
    @Transactional(readOnly = true)
    public List<GamePlayer> list() {
        return (List<GamePlayer>) gamePlayerDao.findAll();
    }

    @Override
    @Transactional
    public GamePlayer save(GamePlayer gamePlayer) {
        return gamePlayerDao.save(gamePlayer);
    }

    @Override
    @Transactional
    public GamePlayer update(Long id, GamePlayer gamePlayer) {
        gamePlayer.setId(id);
        return gamePlayerDao.save(gamePlayer);
    }

    @Override
    @Transactional
    public void delete(GamePlayer gamePlayer) {
        gamePlayerDao.delete(gamePlayer);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GamePlayer> findContact(GamePlayer gamePlayer) {
        return gamePlayerDao.findById(gamePlayer.getId());
    }
}
