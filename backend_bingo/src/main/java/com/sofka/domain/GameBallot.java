package com.sofka.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * clase para mapear la tabla de las balotas
 * @version 1.0.0 2002-03-12
 * @author Kevin Luis Florez Lozada
 * @since 1.0.0
 */

@Data
@Entity
@Table(name="game_ballot")
public class GameBallot implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * atributos correspondientes a columnas de la tabla
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game_ballot")
    private Long id;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "ballot")
    private Long ballot;

}
