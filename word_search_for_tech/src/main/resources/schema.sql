DROP ALL OBJECTS;

CREATE TABLE IF NOT EXISTS t_user(
	user_id INT PRIMARY KEY,
	username VARCHAR UNIQUE NOT NULL,
	address VARCHAR UNIQUE,
	password CHAR(60) NOT NULL,
	point INT NOT NULL,
	seq_count INT NOT NULL,
	longest_seq_count INT NOT NULL
);

CREATE TABLE IF NOT EXISTS t_login_history(
	login_history_id INT PRIMARY KEY,
	user_id VARCHAR NOT NULL,
	created_at TIMESTAMP NOT NULL,
	FOREIGN KEY (user_id) REFERENCES t_user(user_id)
);

CREATE TABLE IF NOT EXISTS t_category(
	category_id CHAR(2) PRIMARY KEY,
	category_name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS t_level(
	level_id CHAR(2) PRIMARY KEY,
	level_name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS t_quiz(
	quiz_id CHAR(6) PRIMARY KEY,
	category_id CHAR(2) NOT NULL,
	level_id CHAR(2) NOT NULL,
	point INT NOT NULL,
	FOREIGN KEY (category_id) REFERENCES t_category(category_id),
	FOREIGN KEY (level_id) REFERENCES t_level(level_id)
);

CREATE TABLE IF NOT EXISTS t_kw(
	kw_id INT AUTO_INCREMENT PRIMARY KEY,
	kw_name VARCHAR
);

CREATE INDEX idx_kw_id ON t_kw(kw_id);

CREATE TABLE IF NOT EXISTS t_question(
	quiz_id CHAR(6),
	kw_id VARCHAR,
	FOREIGN KEY(quiz_id) REFERENCES t_quiz(quiz_id),
	FOREIGN KEY(kw_id) REFERENCES t_kw(kw_id),
	PRIMARY KEY(quiz_id, kw_id)
);

CREATE TABLE IF NOT EXISTS t_game_history(
	game_history_id INT PRIMARY KEY,
	user_id INT NOT NULL,
	quiz_id INT NOT NULL,
	display INT NOT NULL,
	board VARCHAR NOT NULL,
	answer_code VARCHAR NOT NULL,
	point INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	cleared_at TIMESTAMP,
	FOREIGN KEY(user_id) REFERENCES t_user(user_id),
	FOREIGN KEY(quiz_id) REFERENCES t_quiz(quiz_id)
);

CREATE TABLE IF NOT EXISTS t_favorite(
	favoite_id INT PRIMARY KEY,
	user_id INT NOT NULL,
	quiz_id INT NOT NULL,
	memo VARCHAR(140),
	created_at TIMESTAMP NOT NULL,
	updated_at TIMESTAMP NOT NULL
);


CREATE TABLE IF NOT EXISTS m_user(
	user_id VARCHAR(50)PRIMARY KEY,
	password VARCHAR(100),
	user_name VARCHAR(50),
	birthday DATE,
	age INT,
	marriage BOOLEAN,
	role VARCHAR(50)
);
