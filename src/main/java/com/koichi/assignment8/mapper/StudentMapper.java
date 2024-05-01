package com.koichi.assignment8.mapper;

import com.koichi.assignment8.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StudentMapper {
    @Select("SELECT * FROM students WHERE id LIKE CONCAT(#{id}, '%') ")
    Optional<Student> findById(Integer id);

    @Select("SELECT * FROM students")
    List<Student> findAllStudents();

    @Select("SELECT *  from students WHERE grade LIKE CONCAT(#{grade}, '%') ")
    List<Student> findByGrade(Integer grade);

    @Select("SELECT * FROM students WHERE name LIKE CONCAT(#{startsWith}, '%') ")
    List<Student> findByName(String startsWith);

    @Select("SELECT * FROM students WHERE birth_place LIKE CONCAT(#{birthplace}, '%') ")
    List<Student> findByBirthPlace(String birthPlace);


    /**
     * INSERT用のMapper
     */
    @Insert("INSERT INTO students (name,grade,birth_place) VALUES (#{name}, #{grade},#{birthPlace})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Student student);


    /**
     * PATCH用のMapper
     */
    @Update("UPDATE students SET name = #{name}, grade = #{grade},birth_Place = #{birthPlace} WHERE id =#{id}")
    void updateStudent(Student student);


}
