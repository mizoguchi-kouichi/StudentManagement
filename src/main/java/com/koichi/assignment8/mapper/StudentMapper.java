package com.koichi.assignment8.mapper;

import com.koichi.assignment8.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StudentMapper {

    /**
     * 指定したidの学生のデータを取得します。
     */
    @Select("SELECT * FROM students WHERE id = #{id} ")
    Optional<Student> findById(Integer id);

    /**
     * 全ての学生のデータを取得します。
     */
    @Select("SELECT * FROM students")
    List<Student> findAllStudents();

    /**
     * 指定した学年の学生のデータを取得します。
     */
    @Select("SELECT *  from students WHERE grade = #{grade} ")
    List<Student> findByGrade(String grade);

    /**
     * 指定した接頭辞の学生のデータを取得します。
     */
    @Select("SELECT * FROM students WHERE name LIKE CONCAT(#{startsWith}, '%') ")
    List<Student> findByStartWith(String startsWith);

    /**
     * 指定した出身地の学生のデータを取得します。
     */
    @Select("SELECT * FROM students WHERE birth_place = #{birthPlace}")
    List<Student> findByBirthPlace(String birthPlace);

    /**
     * 新しい学生を登録します。
     */
    @Insert("INSERT INTO students (name,grade,birth_place) VALUES (#{name}, #{grade},#{birthPlace})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertStudent(Student student);

    /**
     * 指定したidの学生の名前、学年、出身地を更新します。
     */
    @Update("UPDATE students SET name = #{name}, grade = #{grade},birth_Place = #{birthPlace} WHERE id =#{id}")
    void updateStudent(Student student);

    /**
     * 全学生の学年を一斉に進級させます。
     */
    @Update("UPDATE students SET grade = #{newGrade} WHERE grade =#{grade} ")
    void updateGrade(String newGrade, String grade);

    /**
     * 指定したidの学生のデータを削除します。
     */
    @Delete(" DELETE FROM students WHERE id =#{id}")
    void deleteStudent(Integer id);
}
