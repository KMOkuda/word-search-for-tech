DROP ALL OBJECTS;

CREATE TABLE IF NOT EXISTS t_category(
	category_id INT PRIMARY KEY AUTO_INCREMENT,
	category_name VARCHAR(50) NOT NULL,
	category_description VARCHAR(100),
	category_class VARCHAR(50) NOT NULL
);

INSERT INTO t_category (category_name,category_description,category_class) VALUES ('ハードウェア','','hardware');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('ソフトウェア','','software');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('データベース','','database');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('ネットワーク','','network');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('セキュリティ','','security');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('システム設計','','design');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('マネジメント','','management');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('ストラテジー','','strategy');

CREATE TABLE IF NOT EXISTS t_level(
	level_id INT PRIMARY KEY AUTO_INCREMENT,
	level_name VARCHAR(50) NOT NULL,
	level_description VARCHAR(100)
);

INSERT INTO t_level (level_name,level_description) VALUES ('ITパスポート','');
INSERT INTO t_level (level_name,level_description) VALUES ('基本情報','');
INSERT INTO t_level (level_name,level_description) VALUES ('応用情報','');

/**
CREATE TABLE IF NOT EXISTS t_user(
	user_id INT PRIMARY KEY,
	username VARCHAR UNIQUE NOT NULL,
	address VARCHAR UNIQUE,
	password CHAR(60) NOT NULL,
	point INT NOT NULL,
	seq_count INT NOT NULL,
	longest_seq_count INT NOT NULL
);


CREATE TABLE IF NOT EXISTS t_category(
	category_id CHAR(2) PRIMARY KEY,
	category_name VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS t_level(
	level_id CHAR(2) PRIMARY KEY,
	level_name VARCHAR NOT NULL
);


CREATE TABLE IF NOT EXISTS t_kw(
	kw_id INT AUTO_INCREMENT PRIMARY KEY,
	kw_name VARCHAR
);

**/
