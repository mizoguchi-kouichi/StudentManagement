package com.koichi.assignment8.service;

import com.koichi.assignment8.entity.Student;
import com.koichi.assignment8.excption.MultipleMethodsException;
import com.koichi.assignment8.excption.StudentNotFoundException;
import com.koichi.assignment8.mapper.StudentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public Student findStudent(int id) {
        Optional<Student> findById = this.studentMapper.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new StudentNotFoundException("student not found");
        }
    }


    /**
     * SELECT用のMapper
     * 指定した検索パラメータに一致するstudentのデータを取得します。
     * 指定するパラメータがない場合、全てのstudentのデータを取得します。
     */
    public List<Student> findAllStudents(String grade, String startsWith, String birthPlace) {

        Map<String, String> gradeConvertedToString = new HashMap<>();
        gradeConvertedToString.put("1", "一年生");
        gradeConvertedToString.put("2", "二年生");
        gradeConvertedToString.put("3", "三年生");
        gradeConvertedToString.put("4", "卒業生");


        List<Student> getAllStudent = this.studentMapper.findAllStudents();
        List<Student> getByGrade = this.studentMapper.findByGrade(gradeConvertedToString.get(grade));
        List<Student> getByStartWith = this.studentMapper.findByStartWith(startsWith);
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
    public Student insertStudent(String name, String grade, String birthPlace) {
        Student student = new Student(name, grade, birthPlace);
        studentMapper.insertStudent(student);
        return student;
    }

    /**
     * PATCH用のService
     * 指定したidのstudentの name,grade,birthplaceを更新します。
     */
    public void updateStudent(Integer id, String name, String grade, String birthPlace) {
        Optional<Student> optionalStudent = studentMapper.findById(id);

        Student student = optionalStudent.orElseThrow(() -> new StudentNotFoundException("student not found"));
        student.setName(name);
        student.setGrade(grade);
        student.setBirthPlace(birthPlace);
        studentMapper.updateStudent(student);
    }

    /**
     * PATCH用のService
     * 指定したgradeを進級します。
     */
    @Transactional()
    public void updateGrade() {
        studentMapper.updateGrade("卒業生", "三年生");
        studentMapper.updateGrade("三年生", "二年生");
        studentMapper.updateGrade("二年生", "一年生");
    }

    /**
     * DELETE用のService
     * 指定したidのstudentのデータを削除します。
     */
    public void deleteStudent(Integer id) {
        Optional<Student> findById = this.studentMapper.findById(id);
        Student deleteStudent = findById.orElseThrow(() -> new StudentNotFoundException("student not found"));
        studentMapper.deleteStudent(id);
    }

}
