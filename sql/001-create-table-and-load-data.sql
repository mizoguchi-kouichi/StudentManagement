DROP TABLE IF EXISTS students;

CREATE TABLE students (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 grade int NOT NULL,
 birth_place VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students (name,grade,birth_place) VALUES ('溝口光一',1,"大分県");
INSERT INTO students (name,grade,birth_place) VALUES ("海崎健斗",1,"福岡県");

INSERT INTO students (name,grade,birth_place) VALUES ('溝口健太',2,"福岡県");
INSERT INTO students (name,grade,birth_place) VALUES ("海野芽衣",2,"熊本県");

INSERT INTO students (name,grade,birth_place) VALUES ('溝口千尋',3,"熊本県");
INSERT INTO students (name,grade,birth_place) VALUES ("春夏冬徹",3,"大分県");


