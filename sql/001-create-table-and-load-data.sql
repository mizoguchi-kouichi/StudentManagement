DROP TABLE IF EXISTS students;

CREATE TABLE students (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(20) NOT NULL,
 schoolyear VARCHAR(20) NOT NULL,
 birthplace VARCHAR(20) NOT NULL,
 PRIMARY KEY(id)
);

INSERT INTO students (name,schoolyear,birthplace) VALUES ('溝口光一',"1年生","大分県");
INSERT INTO students (name,schoolyear,birthplace) VALUES ("海崎健斗","1年生","福岡県");

INSERT INTO students (name,schoolyear,birthplace) VALUES ('溝口健太',"2年生","福岡県");
INSERT INTO students (name,schoolyear,birthplace) VALUES ("海野芽衣","2年生","熊本県");

INSERT INTO students (name,schoolyear,birthplace) VALUES ('溝口千尋',"3年生","熊本県");
INSERT INTO students (name,schoolyear,birthplace) VALUES ("春夏冬徹","3年生","大分県");

