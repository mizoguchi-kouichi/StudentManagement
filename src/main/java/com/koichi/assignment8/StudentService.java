package com.koichi.assignment8;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public Student findStudent(Integer id) {
        Optional<Student> findById = this.studentMapper.findById(id);
        if (findById.isPresent()) {
            return findById.get();
        } else {
            throw new StudentNotFoundException("user not found");
        }
    }

    public List<Student> findAllStudents(Integer grade, String startsWith, String birthPlace) {
        List<Student> getAllStudent = this.studentMapper.findAllStudents();
        List<Student> getByGrade = this.studentMapper.findByGrade(grade);
        List<Student> getByStartWith = this.studentMapper.findByName(startsWith);
        List<Student> getByBirthPlace = this.studentMapper.findByBirthPlace(birthPlace);

        if (grade == null && startsWith == null && birthPlace == null) {
            return getAllStudent;
        } else if (startsWith == null && birthPlace == null) {
            return getByGrade;
        } else if (grade == null && birthPlace == null) {
            return getByStartWith;
        } else if (grade == null && startsWith == null) {
            return getByBirthPlace;
        } else {
            throw new MultipleMethodsException("カラムはgrade・startsWith・birthPlaceの一つを選んでください");
        }
    }
}