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

    @GetMapping("/students1")
    public List<Student>findAll(){
        return  studentMapper.findAll1();
    }

    @GetMapping("/students2")
    public List<Student> findAll2() {
        return studentMapper.findAll2();
    }

    @GetMapping("/students3")
    public List<Student> findAll3() {
        return studentMapper.findAll3();
    }

    @GetMapping("/students4")
    public List<Student> findAll4() {
        return studentMapper.findAll4();
    }

    @GetMapping("/students5")
    public List<Student> findByNameStartingWith(@RequestParam String startsWith) {
        return studentMapper.findByStudents1StartingWith(startsWith);
    }

    @GetMapping("/students6")
    public List<Student> findByStudents2id(@RequestParam int id) {
        return studentMapper.findByStudents2id(id);
    }

    @GetMapping("/students7")
    public List<Student> findByStudents3birthplace(@RequestParam String birthplace) {
        return studentMapper.findByStudents3birthplace(birthplace);
    }

    @GetMapping("/students8")
    public List<Student> findByStudent123id(@RequestParam int id) {
        return studentMapper.findByStudents123id(id);
    }

}
