package com.sofka.dao;

import com.sofka.domain.GameBallot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Collection;

/**
 * interfaz DAO para las sentencias SQL de las balotas
 * @version 1.0.0 2002-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

public interface GameBallotDao extends CrudRepository<GameBallot, Long> {

    @Query("SELECT gb.ballot FROM GameBallot gb WHERE gb.gameId = :gameId")
    public ArrayList<Integer> ballotOut(@Param(value = "gameId") Long gameId);
}
