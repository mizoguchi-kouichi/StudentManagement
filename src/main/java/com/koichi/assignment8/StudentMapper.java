package com.koichi.assignment8;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT *  from students WHERE school_year LIKE CONCAT(#{grade}, '%') ")
    List<Student> findByGrade(Integer grade);

    @Select("SELECT * FROM students")
    List<Student> findAll_students();

    @Select("SELECT * FROM students WHERE id LIKE CONCAT(#{id}, '%') ")
    List<Student> findBystudentsid(Integer id);


    @Select("SELECT * FROM students WHERE name LIKE CONCAT(#{startsWith}, '%') ")
    List<Student> findBystudentsname(String startsWith);


    @Select("SELECT * FROM students WHERE birthplace LIKE CONCAT(#{birthplace}, '%') ")
    List<Student> findBystudentsbirthplace(String birthplace);


}