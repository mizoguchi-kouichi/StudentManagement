package com.koichi.assignment8.mapper;

import com.koichi.assignment8.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StudentMapper {

    /**
     * SELECT用のMapper
     * 指定したidのstudentのデータを全て取得します。
     */
    @Select("SELECT * FROM students WHERE id LIKE CONCAT(#{id}, '%') ")
    Optional<Student> findById(Integer id);

    /**
     * SELECT用のMapper
     * 全てのstudentのデータを全て取得します。
     */
    @Select("SELECT * FROM students")
    List<Student> findAllStudents();

    /**
     * SELECT用のMapper
     * 指定した学年のstudentのデータを全て取得します。
     */
    @Select("SELECT *  from students WHERE grade LIKE CONCAT(#{grade}, '%') ")
    List<Student> findByGrade(Integer grade);

    /**
     * SELECT用のMapper
     * 指定した接頭辞のstudentのデータを全て取得します。
     */
    @Select("SELECT * FROM students WHERE name LIKE CONCAT(#{startsWith}, '%') ")
    List<Student> findByName(String startsWith);

    /**
     * SELECT用のMapper
     * 指定した出身地のstudentのデータを全て取得します。
     */
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
     * 指定したidのstudentの name,grade,birthplaceを更新します。
     */
    @Update("UPDATE students SET name = #{name}, grade = #{grade},birth_Place = #{birthPlace} WHERE id =#{id}")
    void updateStudent(Student student);

    /**
     * PATCH用のMapper
     * 指定したgradeのstudentをnewGradeに更新します。
     */
    @Update("UPDATE students SET grade = #{newGrade} WHERE grade =#{grade} ")
    void updateGrade(Student student);


    /**
     * DELETE用のMapper
     * 指定したidのstudentのデータを削除します。
     */
    @Delete(" DELETE FROM students WHERE id =#{id}")
    void deleteStudent(Student student);
}
