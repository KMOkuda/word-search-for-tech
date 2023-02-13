DROP ALL OBJECTS;

CREATE TABLE IF NOT EXISTS t_category(
	category_id INT PRIMARY KEY AUTO_INCREMENT,
	category_name VARCHAR(50) NOT NULL,
	category_description VARCHAR(100),
	category_class VARCHAR(50) NOT NULL
);

INSERT INTO t_category (category_name,category_description,category_class) VALUES ('ソフトウェア','','software');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('セキュリティ','','security');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('ネットワーク','','network');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('データベース','','database');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('ストラテジー','','strategy');
INSERT INTO t_category (category_name,category_description,category_class) VALUES ('論理演算・データ構造','','logic');

CREATE TABLE IF NOT EXISTS t_level(
	level_id INT PRIMARY KEY AUTO_INCREMENT,
	level_name VARCHAR(50) NOT NULL,
	level_description VARCHAR(100)
);

INSERT INTO t_level (level_name,level_description) VALUES ('ITパスポート','');
INSERT INTO t_level (level_name,level_description) VALUES ('基本情報','');
INSERT INTO t_level (level_name,level_description) VALUES ('応用情報','');

CREATE TABLE t_kw(kw_id INT PRIMARY KEY AUTO_INCREMENT,kw VARCHAR(10));
INSERT INTO t_kw (kw) VALUES ('and');
INSERT INTO t_kw (kw) VALUES ('nand');
INSERT INTO t_kw (kw) VALUES ('or');
INSERT INTO t_kw (kw) VALUES ('xor');
INSERT INTO t_kw (kw) VALUES ('not');
INSERT INTO t_kw (kw) VALUES ('bit');
INSERT INTO t_kw (kw) VALUES ('byte');
INSERT INTO t_kw (kw) VALUES ('pop');
INSERT INTO t_kw (kw) VALUES ('push');
INSERT INTO t_kw (kw) VALUES ('que');
INSERT INTO t_kw (kw) VALUES ('stack');
INSERT INTO t_kw (kw) VALUES ('api');
INSERT INTO t_kw (kw) VALUES ('ajax');
INSERT INTO t_kw (kw) VALUES ('cms');
INSERT INTO t_kw (kw) VALUES ('asp');
INSERT INTO t_kw (kw) VALUES ('ide');
INSERT INTO t_kw (kw) VALUES ('os');
INSERT INTO t_kw (kw) VALUES ('apache');
INSERT INTO t_kw (kw) VALUES ('tomcat');
INSERT INTO t_kw (kw) VALUES ('JDK');
INSERT INTO t_kw (kw) VALUES ('module');
INSERT INTO t_kw (kw) VALUES ('driver');
INSERT INTO t_kw (kw) VALUES ('ux');
INSERT INTO t_kw (kw) VALUES ('ui');
INSERT INTO t_kw (kw) VALUES ('agile');
INSERT INTO t_kw (kw) VALUES ('compiler');
INSERT INTO t_kw (kw) VALUES ('html');
INSERT INTO t_kw (kw) VALUES ('css');
INSERT INTO t_kw (kw) VALUES ('python');
INSERT INTO t_kw (kw) VALUES ('java');
INSERT INTO t_kw (kw) VALUES ('scram');
INSERT INTO t_kw (kw) VALUES ('stab');
INSERT INTO t_kw (kw) VALUES ('aes');
INSERT INTO t_kw (kw) VALUES ('dos');
INSERT INTO t_kw (kw) VALUES ('ddos');
INSERT INTO t_kw (kw) VALUES ('rsa');
INSERT INTO t_kw (kw) VALUES ('tkip');
INSERT INTO t_kw (kw) VALUES ('wep');
INSERT INTO t_kw (kw) VALUES ('rasis');
INSERT INTO t_kw (kw) VALUES ('arp');
INSERT INTO t_kw (kw) VALUES ('rarp');
INSERT INTO t_kw (kw) VALUES ('dns');
INSERT INTO t_kw (kw) VALUES ('smtp');
INSERT INTO t_kw (kw) VALUES ('pop');
INSERT INTO t_kw (kw) VALUES ('http');
INSERT INTO t_kw (kw) VALUES ('tls');
INSERT INTO t_kw (kw) VALUES ('ssh');
INSERT INTO t_kw (kw) VALUES ('ip');
INSERT INTO t_kw (kw) VALUES ('fqdn');
INSERT INTO t_kw (kw) VALUES ('cloud');
INSERT INTO t_kw (kw) VALUES ('cookie');
INSERT INTO t_kw (kw) VALUES ('session');
INSERT INTO t_kw (kw) VALUES ('acid');
INSERT INTO t_kw (kw) VALUES ('mysql');
INSERT INTO t_kw (kw) VALUES ('postgre');
INSERT INTO t_kw (kw) VALUES ('nosql');
INSERT INTO t_kw (kw) VALUES ('backup');
INSERT INTO t_kw (kw) VALUES ('rollback');
INSERT INTO t_kw (kw) VALUES ('forward');
INSERT INTO t_kw (kw) VALUES ('select');
INSERT INTO t_kw (kw) VALUES ('from');
INSERT INTO t_kw (kw) VALUES ('table');
INSERT INTO t_kw (kw) VALUES ('where');
INSERT INTO t_kw (kw) VALUES ('in');
INSERT INTO t_kw (kw) VALUES ('exists');
INSERT INTO t_kw (kw) VALUES ('between');
INSERT INTO t_kw (kw) VALUES ('groupby');
INSERT INTO t_kw (kw) VALUES ('asc');
INSERT INTO t_kw (kw) VALUES ('desc');
INSERT INTO t_kw (kw) VALUES ('orderby');
INSERT INTO t_kw (kw) VALUES ('join');
INSERT INTO t_kw (kw) VALUES ('update');
INSERT INTO t_kw (kw) VALUES ('create');
INSERT INTO t_kw (kw) VALUES ('insert');
INSERT INTO t_kw (kw) VALUES ('into');
INSERT INTO t_kw (kw) VALUES ('set');
INSERT INTO t_kw (kw) VALUES ('values');
INSERT INTO t_kw (kw) VALUES ('push');
INSERT INTO t_kw (kw) VALUES ('commit');
INSERT INTO t_kw (kw) VALUES ('pull');
INSERT INTO t_kw (kw) VALUES ('stash');
INSERT INTO t_kw (kw) VALUES ('merge');
INSERT INTO t_kw (kw) VALUES ('rebase');
INSERT INTO t_kw (kw) VALUES ('fetch');
INSERT INTO t_kw (kw) VALUES ('branch');
INSERT INTO t_kw (kw) VALUES ('close');
INSERT INTO t_kw (kw) VALUES ('conflict');
INSERT INTO t_kw (kw) VALUES ('index');
INSERT INTO t_kw (kw) VALUES ('revert');
INSERT INTO t_kw (kw) VALUES ('SIer');
INSERT INTO t_kw (kw) VALUES ('Saas');
INSERT INTO t_kw (kw) VALUES ('IaaS');
INSERT INTO t_kw (kw) VALUES ('PaaS');
INSERT INTO t_kw (kw) VALUES ('bi');
INSERT INTO t_kw (kw) VALUES ('bcm');
INSERT INTO t_kw (kw) VALUES ('bcp');
INSERT INTO t_kw (kw) VALUES ('bsc');
INSERT INTO t_kw (kw) VALUES ('scm');

CREATE TABLE t_puzzle(
puzzle_id VARCHAR PRIMARY KEY,
category_id INT, level_id INT,
height INT,
width INT
FOREIGN KEY (category_id)
REFERENCES t_category(category_id)
);


