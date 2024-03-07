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

    @GetMapping("/students/first-grade")
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


    @GetMapping("/students/thirdgrade/birthplace")
    public List<Student> findBythirdgradebirthplace(@RequestParam String birthplace) {
        return studentMapper.findBythirdgradebirthplace(birthplace);
    }


    @GetMapping("/students/allgrade/id_search")
    public List<Student> findByallstudentid(@RequestParam int id)
    {  return studentMapper.findByallstudentid(id);
    }


    @GetMapping("/students/allgrade/name_search")
    public List<Student> findByallstudentname(@RequestParam String startsWith) {
        return studentMapper.findByallstudentname(startsWith);
    }

    @GetMapping("/students/allgrade/birthplace_search")
    public List<Student> findByallstudentbirthplace(@RequestParam String birthplace) {
        return studentMapper.findByallstudentbirthplace(birthplace);
    }


}
