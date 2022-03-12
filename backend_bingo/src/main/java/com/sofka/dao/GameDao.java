package com.sofka.dao;

import com.sofka.domain.Game;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameDao extends CrudRepository<Game, Long> {
    @Modifying
    @Query("UPDATE Game gam SET gam.inProgress = 'false', gam.gameWinner = :gameWinner, gam.cardBoard = :cardBoard WHERE gam.id = :id")
    public void updateWinner(@Param(value = "id") Long id,
                           @Param(value = "gameWinner") String gameWinner,
                             @Param(value = "cardBoard") String cardBoard);
}
