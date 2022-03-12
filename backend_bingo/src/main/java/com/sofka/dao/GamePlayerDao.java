package com.sofka.dao;

import com.sofka.domain.GamePlayer;
import org.springframework.data.repository.CrudRepository;

public interface GamePlayerDao extends CrudRepository<GamePlayer, Long> {

}
