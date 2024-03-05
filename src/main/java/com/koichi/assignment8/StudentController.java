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

    @GetMapping("/students/firstgrade")
    public List<Student>findAll_firstgrade(){
        return  studentMapper.findAll_firstgrade();
    }

    @GetMapping("/students/secondgrade")
    public List<Student> findAll_secondgrade() {
        return studentMapper.findAll_secondgrade();
    }


    @GetMapping("/students/thirdgrade")
    public List<Student> findAll_thirdgrade() {
        return studentMapper.findAll_thidgrade();
    }


    @GetMapping("/students/allgrade")
    public List<Student> findAll_allstudent() {
        return studentMapper.findAll_allstudent();
    }

    @GetMapping("/students/firstgrade/name")
    public List<Student> findByfirstgradeStartingWith(@RequestParam String startsWith) {
        return studentMapper.findByfirstgradeStartingWith(startsWith);
    }


    @GetMapping("/students/secondgrade/id")
    public List<Student> findBysecondgradeid(@RequestParam int id) {
        return studentMapper.findBysecondgradeid(id);
    }


    @GetMapping("/students7")
    public List<Student> findByStudents3birthplace(@RequestParam String birthplace) {
        return studentMapper.findByStudents3birthplace(birthplace);
    }

    @GetMapping("/students8")
    public List<Student> findByStudent123id(@RequestParam int id) {
        return studentMapper.findByStudents123id(id);
    }

    @GetMapping("/students9")
    public List<Student> findByStudent123name(@RequestParam String name) {
        return studentMapper.findByStudents123name(name);
    }

    @GetMapping("/students10")
    public List<Student> findByStudent123birthplace(@RequestParam String birthplace) {
        return studentMapper.findByStudents123birthplace(birthplace);
    }

}
