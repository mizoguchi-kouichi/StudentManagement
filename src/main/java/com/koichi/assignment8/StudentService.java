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

    public Optional<List<Student>> getAllStudent() {
        List<Student> getAllStudents = this.studentMapper.findAllStudents();
        return Optional.ofNullable(getAllStudents);
    }

}