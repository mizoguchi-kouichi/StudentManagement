package com.koichi.assignment8.service;

import com.koichi.assignment8.entity.Student;
import com.koichi.assignment8.excption.IncorrectGradeException;
import com.koichi.assignment8.excption.MultipleMethodsException;
import com.koichi.assignment8.excption.StudentNotFoundException;
import com.koichi.assignment8.mapper.StudentMapper;
import org.springframework.stereotype.Service;

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
     * 指定した検索パラメータに一致するstudentのデータを取得します。
     * 指定するパラメータがない場合、全てのstudentのデータを取得します。
     */
    public List<Student> findAllStudents(Integer schoolYear, String startsWith, String birthPlace) {

        Map<Integer, String> grade = new HashMap<>();
        grade.put(1, "一年生");
        grade.put(2, "二年生");
        grade.put(3, "三年生");
        grade.put(4, "卒業生");

        List<Student> getAllStudent = this.studentMapper.findAllStudents();
        List<Student> getByGrade = this.studentMapper.findByGrade(grade.get(schoolYear));
        List<Student> getByStartWith = this.studentMapper.findByName(startsWith);
        List<Student> getByBirthPlace = this.studentMapper.findByBirthPlace(birthPlace);

        int count = 0;

        if (schoolYear != null) {
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
        } else if (schoolYear != null) {
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
    public Student insert(String name, String grade, String birthPlace) {
        Student student = new Student(name, grade, birthPlace);
        studentMapper.insert(student);
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
    public List<Student> updateGrade(Integer schoolYear) {

        Map<Integer, String> grade = new HashMap<>();
        grade.put(1, "一年生");
        grade.put(2, "二年生");
        grade.put(3, "三年生");

        List<Student> updateGradeStudents = studentMapper.findByGrade(grade.get(schoolYear));

        if (schoolYear == 1) {
            for (Student student : updateGradeStudents) {
                student.setNewGrade("二年生");
                studentMapper.updateGrade(student);
            }
        } else if (schoolYear == 2) {
            for (Student student : updateGradeStudents) {
                student.setNewGrade("三年生");
                studentMapper.updateGrade(student);
            }
        } else if (schoolYear == 3) {
            for (Student student : updateGradeStudents) {
                student.setNewGrade("卒業生");
                studentMapper.updateGrade(student);
            }
        } else {
            throw new IncorrectGradeException("有効な学年を指定してください（1,2,3のいずれか）。");
        }
        return updateGradeStudents;
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
