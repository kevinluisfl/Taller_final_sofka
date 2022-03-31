package com.sofka.dao;

import com.sofka.domain.GamePlayer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * interfaz DAO para las sentencias SQL de los jugadores
 * @version 1.0.0 2022-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

public interface GamePlayerDao extends CrudRepository<GamePlayer, Long> {
    @Modifying
    @Query("UPDATE GamePlayer gp SET gp.disqualified = 'true' WHERE gp.gameId = :gameId AND gp.player = :player")
    public void updateLoser(@Param(value = "gameId") Long gameId,
                            @Param(value = "player") String player);
}
