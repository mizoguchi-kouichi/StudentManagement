package com.koichi.assignment8;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT *  from students " +
            "WHERE schoolyear IN ('1年生')")
    List<Student> findAll_firstgrade();

    @Select("SELECT *  from students " +
            "WHERE schoolyear IN ('2年生')")
    List<Student> findAll_scondgrade();

    @Select("SELECT *  from students " +
            "WHERE schoolyear IN ('3年生')")
    List<Student> findAll_thirdgrade();

    @Select("SELECT * FROM students")
    List<Student> findAll_students();

    @Select("SELECT * FROM students WHERE id LIKE CONCAT(#{id}, '%') ")
    List<Student> findBystudentsid(Integer id);


    @Select("SELECT * FROM students WHERE name LIKE CONCAT(#{startsWith}, '%') ")
    List<Student> findBystudentsname(String startsWith);


    @Select("SELECT * FROM students_first_grade WHERE birthplace LIKE CONCAT(#{birthplace}, '%') " +
            "UNION SELECT * FROM students_second_grade WHERE birthplace LIKE CONCAT(#{birthplace}, '%') " +
            "UNION SELECT * FROM students_third_grade WHERE birthplace LIKE CONCAT(#{birthplace}, '%')")
    List<Student> findByallstudentbirthplace(String birthplace);


}