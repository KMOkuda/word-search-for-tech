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
puzzle_id VARCHAR(20) PRIMARY KEY,
category_id INT, level_id INT,
height INT,
width INT,
FOREIGN KEY (category_id)
REFERENCES t_category(category_id),
FOREIGN KEY (level_id)
REFERENCES t_level(level_id)
);

INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('060101',6,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('010101',1,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('010102',1,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('010103',1,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('020101',2,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('030101',3,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('030102',3,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('040101',4,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('040102',4,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('040103',4,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('040104',4,1,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('010201',1,2,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('010202',1,2,8,8);
INSERT INTO t_puzzle (puzzle_id,category_id,level_id,height,width) VALUES ('050101',5,1,8,8);

CREATE TABLE t_kw_property(
puzzle_kw_id INT PRIMARY KEY AUTO_INCREMENT,
kw_id INT,
puzzle_id VARCHAR(20),
FOREIGN KEY (kw_id)
REFERENCES t_kw(kw_id),
FOREIGN KEY (puzzle_id)
REFERENCES t_puzzle(puzzle_id)
);

INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (1,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (2,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (3,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (4,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (5,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (6,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (7,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (8,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (9,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (10,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (11,'060101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (12,'010101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (13,'010101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (14,'010101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (15,'010101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (16,'010101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (17,'010101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (18,'010101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (19,'010101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (20,'010102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (21,'010102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (22,'010102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (23,'010102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (24,'010102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (25,'010102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (26,'010102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (27,'010103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (28,'010103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (29,'010103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (30,'010103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (31,'010103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (32,'010103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (33,'020101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (34,'020101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (35,'020101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (36,'020101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (37,'020101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (38,'020101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (39,'020101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (40,'030101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (41,'030101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (42,'030101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (43,'030101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (44,'030101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (45,'030101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (46,'030101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (47,'030102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (48,'030102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (49,'030102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (50,'030102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (51,'030102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (52,'030102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (53,'040101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (54,'040101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (55,'040101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (56,'040101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (57,'040101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (58,'040101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (59,'040101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (60,'040102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (61,'040102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (62,'040102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (63,'040102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (64,'040102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (65,'040102');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (66,'040103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (67,'040103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (68,'040103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (69,'040103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (70,'040103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (71,'040103');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (72,'040104');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (73,'040104');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (74,'040104');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (75,'040104');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (76,'040104');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (77,'040104');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (78,'010201');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (79,'010201');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (80,'010201');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (81,'010201');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (82,'010201');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (83,'010201');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (84,'010202');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (85,'010202');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (86,'010202');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (87,'010202');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (88,'010202');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (89,'010202');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (90,'050101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (91,'050101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (92,'050101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (93,'050101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (94,'050101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (95,'050101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (96,'050101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (97,'050101');
INSERT INTO t_kw_property (kw_id,puzzle_id) VALUES (98,'050101');

CREATE TABLE t_play(
play_id INT PRIMARY KEY AUTO_INCREMENT,
public_id uuid DEFAULT random_uuid(),
puzzle_id VARCHAR(20),
created_at DATETIME,
cleared_at DATETIME,
FOREIGN KEY (puzzle_id)
REFERENCES t_puzzle(puzzle_id)
);
