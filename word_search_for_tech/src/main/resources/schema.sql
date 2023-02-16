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

INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (60101,6,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (10101,1,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (10102,1,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (10103,1,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (20101,2,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (30101,3,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (30102,3,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (40101,4,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (40102,4,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (40103,4,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (40104,4,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (10201,1,2,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (10202,1,2,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES (50101,5,1,8,8);

CREATE TABLE t_kw_property(
puzzle_kw_id INT PRIMARY KEY AUTO_INCREMENT,
kw_id INT,
puzzle_id VARCHAR(20),
FOREIGN KEY (kw_id)
REFERENCES t_kw(kw_id),
FOREIGN KEY (puzzle_id)
REFERENCES t_puzzle(puzzle_id)
);

INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (1,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (2,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (3,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (4,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (5,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (6,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (7,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (8,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (9,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (10,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (11,60101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (12,10101);
--INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (13,10101);
--INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (14,10101);
--INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (15,10101);
--INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (16,10101);
--INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (17,10101);
--INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (18,10101);
--INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (19,10101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (20,10102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (21,10102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (22,10102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (23,10102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (24,10102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (25,10102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (26,10102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (27,10103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (28,10103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (29,10103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (30,10103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (31,10103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (32,10103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (33,20101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (34,20101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (35,20101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (36,20101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (37,20101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (38,20101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (39,20101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (40,30101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (41,30101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (42,30101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (43,30101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (44,30101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (45,30101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (46,30101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (47,30102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (48,30102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (49,30102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (50,30102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (51,30102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (52,30102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (53,40101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (54,40101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (55,40101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (56,40101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (57,40101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (58,40101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (59,40101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (60,40102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (61,40102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (62,40102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (63,40102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (64,40102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (65,40102);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (66,40103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (67,40103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (68,40103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (69,40103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (70,40103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (71,40103);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (72,40104);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (73,40104);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (74,40104);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (75,40104);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (76,40104);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (77,40104);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (78,10201);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (79,10201);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (80,10201);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (81,10201);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (82,10201);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (83,10201);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (84,10202);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (85,10202);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (86,10202);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (87,10202);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (88,10202);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (89,10202);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (90,50101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (91,50101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (92,50101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (93,50101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (94,50101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (95,50101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (96,50101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (97,50101);
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (98,50101);

CREATE TABLE t_play(
play_id INT PRIMARY KEY AUTO_INCREMENT,
public_id uuid DEFAULT random_uuid(),
puzzle_id INT,
created_at TIMESTAMP,
cleared_at TIMESTAMP,
FOREIGN KEY (puzzle_id)
REFERENCES t_puzzle(puzzle_id)
);
