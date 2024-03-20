package com.koichi.assignment8;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public Student findById(Integer id) {
        Optional<Student> student = this.studentMapper.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new StudentNotFoundException("user not found");
        }
    }
}
