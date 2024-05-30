DROP TABLE IF EXISTS students;

CREATE TABLE students (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 grade VARCHAR(20) NOT NULL,
 birth_place VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students (name,grade,birth_place) VALUES ('溝口光一',"一年生","大分県");
INSERT INTO students (name,grade,birth_place) VALUES ("海崎健斗","一年生","福岡県");

INSERT INTO students (name,grade,birth_place) VALUES ('溝口健太',"二年生","福岡県");
INSERT INTO students(name,grade,birth_place) VALUES ("海野芽衣","二年生","熊本県");

INSERT INTO students (name,grade,birth_place) VALUES ('溝口千尋',"三年生","熊本県");
INSERT INTO students (name,grade,birth_place) VALUES ("春夏冬徹","三年生","大分県");

INSERT INTO students (name,grade,birth_place)VALUES ('溝口千尋',"卒業生","熊本県");
INSERT INTO students (name,grade,birth_place)VALUES ("春夏冬徹","卒業生","大分県");

