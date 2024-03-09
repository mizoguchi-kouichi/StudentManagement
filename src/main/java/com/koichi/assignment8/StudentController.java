package com.koichi.assignment8;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    private final StudentMapper studentMapper;

    public StudentController(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @GetMapping("/students")
    public List<Student> findAllStudents(@RequestParam(required = false) Integer grade, String startsWith, String birthPlace) {
        if (grade != null) {
            return studentMapper.findByGrade(grade);
        } else if (startsWith != null) {
            return studentMapper.findByName(startsWith);
        } else if (birthPlace != null) {
            return studentMapper.findByBirthPlace(birthPlace);
        } else {
            return studentMapper.findAllStudents();
        }
    }

    @GetMapping("/students/{id}")
    public List<Student> findById(@PathVariable("id") Integer id) {
        return studentMapper.findById(id);
    }
}
