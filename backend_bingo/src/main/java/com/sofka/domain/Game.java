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

@Data
@Entity
@Table(name="game")
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * atributos correspondientes a columnas de la tabla
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game")
    private Long id;

    @Column(name = "game_date")
    private Date gameDate;

    @Column(name = "in_progress")
    private String inProgress = "true";

    @Column(name = "game_winner")
    private String gameWinner;

    @Column(name = "cardboard")
    private String cardBoard;

    private ArrayList<Integer> cardcheck;
}
