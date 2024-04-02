package com.koichi.assignment8;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM students WHERE id LIKE CONCAT(#{id}, '%') ")
    Optional<Student> findById(Integer id);

    @Select("SELECT * FROM students")
    List<Student> findAllStudents();

    @Select("SELECT *  from students WHERE school_year LIKE CONCAT(#{grade}, '%') ")
    List<Student> findByGrade(Integer grade);

    @Select("SELECT * FROM students WHERE name LIKE CONCAT(#{startsWith}, '%') ")
    List<Student> findByName(String startsWith);

    @Select("SELECT * FROM students WHERE birth_place LIKE CONCAT(#{birthplace}, '%') ")
    List<Student> findByBirthPlace(String birthPlace);

    @Insert("INSERT INTO students (name,school_year,birth_place) VALUES (#{name}, #{grade},#{birthPlace})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Student student);
}