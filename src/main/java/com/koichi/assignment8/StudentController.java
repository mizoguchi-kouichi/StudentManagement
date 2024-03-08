package com.koichi.assignment8;

import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Student> findAll_students(@RequestParam(required = false) Integer id, Integer grade, String startsWith, String birthplace) {
        if (grade != null) {
            return studentMapper.findByGrade(grade);
        } else if (id != null) {
            return studentMapper.findBystudentsid(id);
        } else if (startsWith != null) {
            return studentMapper.findBystudentsname(startsWith);
        } else if (birthplace != null) {
            return studentMapper.findBystudentsbirthplace(birthplace);
        } else {
            return studentMapper.findAll_students();
        }
    }
}
