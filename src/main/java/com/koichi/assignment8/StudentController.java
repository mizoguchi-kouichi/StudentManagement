package com.koichi.assignment8;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students/{id}")
    public Student findById(@PathVariable("id") Integer id) {
        return studentService.findStudent(id);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(@RequestParam(required = false) Integer grade, String startsWith, String birthPlace) {
        return studentService.findAllStudents(grade, startsWith, birthPlace);
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponse> insert(@RequestBody StudentRequest studentRequest, UriComponentsBuilder uriBuilder) {
        Student student = studentService.insert(studentRequest.getName(), studentRequest.getSchoolYear(), studentRequest.getBirthPlace());
        URI location = uriBuilder.path("/students/{id}").buildAndExpand(student.getId()).toUri();
        StudentResponse body = new StudentResponse("student created");
        return ResponseEntity.created(location).body(body);
    }

}
