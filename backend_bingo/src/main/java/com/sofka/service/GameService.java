package com.sofka.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sofka.dao.GameDao;
import com.sofka.domain.Game;

/**
 * clase que implementa metodos abstractos para los juegos
 * @version 1.0.0 2022-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

@Service
public class GameService implements IGameService {

    /**
     * metodos implementados de la interfaz
     */
    @Autowired
    private GameDao gameDao;

    @Override
    @Transactional(readOnly = true)
    public List<Game> list() {
        return (List<Game>) gameDao.findAll();
    }

    @Override
    @Transactional
    public Game save(Game game) {
        return gameDao.save(game);
    }

    @Override
    @Transactional
    public Game update(Long id, Game game) {
        game.setId(id);
        return gameDao.save(game);
    }

    @Override
    @Transactional
    public void delete(Game game) {
        gameDao.delete(game);
    }

    @Transactional
    public void updateWinner(Long id, Game game) {
        gameDao.updateWinner(id,game.getGameWinner(), game.getCardBoard());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Game> findContact(Game game) {
        return gameDao.findById(game.getId());
    }
}
