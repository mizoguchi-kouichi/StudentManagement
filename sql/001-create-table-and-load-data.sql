DROP TABLE IF EXISTS students1;
DROP TABLE IF EXISTS students2;
DROP TABLE IF EXISTS students3;

CREATE TABLE students1 (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 birthplace VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students1 (name,birthplace) VALUES ('溝口光一',"大分県");
INSERT INTO students1 (name,birthplace) VALUES ("海崎健斗","福岡県");

CREATE TABLE students2 (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 birthplace VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students2 (name,birthplace) VALUES ('溝口健太',"福岡県");
INSERT INTO students2 (name,birthplace) VALUES ("海野芽衣","熊本県");

CREATE TABLE students3 (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 birthplace VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students3 (name,birthplace) VALUES ('溝口千尋',"熊本県");
INSERT INTO students3 (name,birthplace) VALUES ("春夏冬徹","大分県");