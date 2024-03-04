DROP TABLE IF EXISTS students_first_grade;
DROP TABLE IF EXISTS students_second_grade;
DROP TABLE IF EXISTS students_third_grade;

CREATE TABLE students_first_grade (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 birthplace VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students_first_grade (name,birthplace) VALUES ('溝口光一',"大分県");
INSERT INTO students_first_grade (name,birthplace) VALUES ("海崎健斗","福岡県");

CREATE TABLE students_second_grade (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 birthplace VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students_second_grade (name,birthplace) VALUES ('溝口健太',"福岡県");
INSERT INTO students_second_grade (name,birthplace) VALUES ("海野芽衣","熊本県");

CREATE TABLE students_third_grade (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 birthplace VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students_third_grade (name,birthplace) VALUES ('溝口千尋',"熊本県");
INSERT INTO students_third_grade (name,birthplace) VALUES ("春夏冬徹","大分県")；
