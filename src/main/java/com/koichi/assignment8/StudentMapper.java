package com.koichi.assignment8;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM students1")
    List<Student>findAll1();

    @Select("SELECT * FROM students2")
    List<Student> findAll2();

    @Select("SELECT * FROM students3")
    List<Student> findAll3();

    @Select("SELECT * FROM students1 UNION SELECT * FROM students2 UNION SELECT * FROM students3")
    List<Student> findAll4();

    @Select("SELECT * FROM students1 WHERE name LIKE CONCAT(#{prefix}, '%')")
    List<Student> findByStudents1StartingWith(String prefix);

    @Select("SELECT * FROM students2 WHERE id LIKE CONCAT(#{id}, '%')")
    List<Student> findByStudents2id(int id);

    @Select("SELECT * FROM students3 WHERE birthplace LIKE CONCAT(#{birthplace}, '%')")
    List<Student> findByStudents3birthplace(String birthplace);

    @Select("SELECT * FROM students1 WHERE id LIKE CONCAT(#{id}, '%') UNION SELECT * FROM students2 WHERE id LIKE CONCAT(#{id}, '%') UNION SELECT * FROM students3 WHERE id LIKE CONCAT(#{id}, '%')")
    List<Student> findByStudents123id(int id);

    @Select("SELECT * FROM students1 WHERE name LIKE CONCAT(#{name}, '%') UNION SELECT * FROM students2 WHERE name LIKE CONCAT(#{name}, '%') UNION SELECT * FROM students3 WHERE name LIKE CONCAT(#{name}, '%')")
    List<Student> findByStudents123name(String name);

    @Select("SELECT * FROM students1 WHERE birthplace LIKE CONCAT(#{birthplace}, '%') UNION SELECT * FROM students2 WHERE birthplace LIKE CONCAT(#{birthplace}, '%') UNION SELECT * FROM students3 WHERE birthplace LIKE CONCAT(#{birthplace}, '%')")
    List<Student> findByStudents123birthplace(String birthplace);
}