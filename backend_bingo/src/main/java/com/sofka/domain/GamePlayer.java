package com.sofka.domain;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * clase para mapear la tabla de los jugadores
 * @version 1.0.0 2002-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

@Data
@Entity
@Table(name="game_player")
public class GamePlayer implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * atributos correspondientes a columnas de la tabla
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game_player")
    private Long id;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "player")
    private String player;

    @Column(name = "cardboard")
    private String cardBoard;

    @Column(name = "disqualified")
    private String disqualified = "false";

    transient private ArrayList<Integer> cardcheck;

}
