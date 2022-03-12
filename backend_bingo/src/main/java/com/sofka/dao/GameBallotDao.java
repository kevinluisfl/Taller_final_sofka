package com.sofka.dao;

import com.sofka.domain.GameBallot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Collection;

public interface GameBallotDao extends CrudRepository<GameBallot, Long> {

    @Query("SELECT gb.ballot FROM GameBallot gb WHERE gb.gameId = :gameId")
    public ArrayList<Integer> ballotOut(@Param(value = "gameId") Long gameId);
}
