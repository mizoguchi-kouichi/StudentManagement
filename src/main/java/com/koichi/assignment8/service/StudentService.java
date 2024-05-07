package com.koichi.assignment8.service;

import com.koichi.assignment8.entity.Student;
import com.koichi.assignment8.excption.MultipleMethodsException;
import com.koichi.assignment8.excption.StudentNotFoundException;
import com.koichi.assignment8.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    /**
     * SELECT用のService
     * 指定したidのstudentのデータを全て取得します。
     */
    public Student findStudent(Integer id) {
        Optional<Student> findById = this.studentMapper.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new StudentNotFoundException("user not found");
        }
    }

    /**
     * SELECT用のMapper
     * クエリパラメータで指定したgrade,startWith,birthPlaceの
     * studentのデータを全ての取得します。
     */
    public List<Student> findAllStudents(Integer grade, String startsWith, String birthPlace) {
        List<Student> getAllStudent = this.studentMapper.findAllStudents();
        List<Student> getByGrade = this.studentMapper.findByGrade(grade);
        List<Student> getByStartWith = this.studentMapper.findByName(startsWith);
        List<Student> getByBirthPlace = this.studentMapper.findByBirthPlace(birthPlace);

        int count = 0;

        if (grade != null) {
            count++;
        }
        if (startsWith != null) {
            count++;
        }

        if (birthPlace != null) {
            count++;
        }

        if (count >= 2) {
            throw new MultipleMethodsException("カラムはgrade・startsWith・birthPlaceの一つを選んでください");
        } else if (grade != null) {
            return getByGrade;
        } else if (startsWith != null) {
            return getByStartWith;
        } else if (birthPlace != null) {
            return getByBirthPlace;
        } else {
            return getAllStudent;
        }
    }

    /**
     * INSERT用のService
     */
    public Student insert(String name, Integer grade, String birthPlace) {
        Student student = new Student(name, grade, birthPlace);
        studentMapper.insert(student);
        return student;
    }

    /**
     * PATCH用のService
     * 指定したidのstudentの name,grade,birthplaceを更新するREAD処理
     */

    public Student updateStudent(Integer id, String name, Integer grade, String birthPlace) {
        Optional<Student> optionalStudent = studentMapper.findById(id);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(name);
            student.setGrade(grade);
            student.setBirthPlace(birthPlace);
            studentMapper.updateStudent(student);
            return student;
        } else {
            throw new StudentNotFoundException("student not found");
        }
    }

    /**
     * PATCH用のService
     * 指定したgradeのstudentをnewGradeに更新するREAD処理
     */
    public List<Student> updateGrade(Integer grade, Integer newGrade) {
        List<Student> updateGradeStudents = studentMapper.findByGrade(grade);

        for (Student student : updateGradeStudents) {
            student.setNewGrade(newGrade);
            studentMapper.updateGrade(student);
        }
        return updateGradeStudents;

    }

}
