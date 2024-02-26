DROP TABLE IF EXISTS students;

CREATE TABLE students (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 birthplace VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students (name,birthplace) VALUES ('溝口光一',"大分県");
INSERT INTO students (name,birthplace) VALUES ("海崎健斗","福岡県");