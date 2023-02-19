--DROP ALL OBJECTS;

CREATE TABLE IF NOT EXISTS t_category(
	category_id INT PRIMARY KEY AUTO_INCREMENT,
	category_name VARCHAR(50) NOT NULL,
	category_description VARCHAR(100),
	category_class VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS t_level(
	level_id INT PRIMARY KEY AUTO_INCREMENT,
	level_name VARCHAR(50) NOT NULL,
	level_description VARCHAR(100)
);


CREATE TABLE t_kw(kw_id INT PRIMARY KEY AUTO_INCREMENT,kw VARCHAR(10));


CREATE TABLE t_puzzle(
puzzle_id INT PRIMARY KEY,
category_id INT,
level_id INT,
height INT,
width INT,
FOREIGN KEY (category_id)
REFERENCES t_category(category_id),
FOREIGN KEY (level_id)
REFERENCES t_level(level_id)
);


CREATE TABLE IF NOT EXISTS t_kw_property(
puzzle_kw_id INT PRIMARY KEY AUTO_INCREMENT,
kw_id INT,
puzzle_id INT,
FOREIGN KEY (kw_id)
REFERENCES t_kw(kw_id),
FOREIGN KEY (puzzle_id)
REFERENCES t_puzzle(puzzle_id),
UNIQUE (kw_id, puzzle_id)
);



CREATE TABLE IF NOT EXISTS t_play(
play_id INT PRIMARY KEY AUTO_INCREMENT,
public_id VARCHAR(500),
puzzle_id INT,
line_board VARCHAR(500),
created_at TIMESTAMP,
cleared_at TIMESTAMP,
FOREIGN KEY (puzzle_id)
REFERENCES t_puzzle(puzzle_id),
UNIQUE (public_id)
);

CREATE TABLE IF NOT EXISTS t_answer(
t_answer INT PRIMARY KEY AUTO_INCREMENT,
order_index INT,
kw_id INT,
play_id INT,
from_id INT,
to_id INT,
answer_flg BOOLEAN,
FOREIGN KEY (kw_id)
REFERENCES t_kw(kw_id),
FOREIGN KEY (play_id)
REFERENCES t_play(play_id)
)
;