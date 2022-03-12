DROP DATABASE if EXISTS bingodb;

CREATE DATABASE bingodb;

USE bingodb;

CREATE TABLE game (
	id_game INT NOT NULL AUTO_INCREMENT,
	game_date DATETIME NOT NULL,
	in_progress ENUM('true','false') NOT NULL DEFAULT 'true',
	game_winner VARCHAR(200) NULL DEFAULT NULL,
	cardboard VARCHAR(200) NULL DEFAULT NULL,
	PRIMARY KEY (id_game)
)ENGINE=InnoDB
COMMENT='information on the games created';

CREATE TABLE game_player (
	id_game_player INT NOT NULL AUTO_INCREMENT,
	game_id INT NOT NULL DEFAULT '0',
	player VARCHAR(200) NOT NULL DEFAULT '',
	cardboard VARCHAR(200) NOT NULL DEFAULT '',
	disqualified ENUM('true','false') NOT NULL DEFAULT 'false',
	PRIMARY KEY (id_game_player),
	INDEX game_id (game_id)
)ENGINE=InnoDB
COMMENT='table that stores the information relating a player to a game';

CREATE TABLE game_ballot (
	id_game_ballot INT NOT NULL AUTO_INCREMENT,
	game_id INT NOT NULL DEFAULT '0',
	ballot INT NOT NULL DEFAULT '0',
	PRIMARY KEY (id_game_ballot),
	INDEX game_id (game_id)
)ENGINE=InnoDB 
COMMENT='table that stores the information of the balls that came out of a game';