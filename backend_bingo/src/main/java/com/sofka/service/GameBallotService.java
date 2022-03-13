package com.sofka.service;

import com.sofka.dao.GameBallotDao;
import com.sofka.domain.GameBallot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * clase que implementa metodos abstractos para las balotas
 * @version 1.0.0 2002-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

@Service
public class GameBallotService implements IGameBallotService {

    /**
     * metodos implementados de la interfaz
     */
    @Autowired
    private GameBallotDao gameBallotDao;

    @Override
    @Transactional(readOnly = true)
    public List<GameBallot> list() {
        return (List<GameBallot>) gameBallotDao.findAll();
    }

    @Override
    @Transactional
    public GameBallot save(GameBallot gameBallot) {
        return gameBallotDao.save(gameBallot);
    }

    @Override
    @Transactional
    public GameBallot update(Long id, GameBallot gameBallot) {
        gameBallot.setId(id);
        return gameBallotDao.save(gameBallot);
    }

    @Override
    @Transactional
    public void delete(GameBallot gameBallot) {
        gameBallotDao.delete(gameBallot);
    }

    @Override
    public ArrayList<Integer> ballotOut(Long id) {
        return gameBallotDao.ballotOut(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GameBallot> findContact(GameBallot gameBallot) {
        return gameBallotDao.findById(gameBallot.getId());
    }
}
