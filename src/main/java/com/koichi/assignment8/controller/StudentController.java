package com.koichi.assignment8.controller;


import com.koichi.assignment8.controller.request.StudentRequest;
import com.koichi.assignment8.controller.request.StudentUpdateRequest;
import com.koichi.assignment8.controller.response.StudentResponse;
import com.koichi.assignment8.entity.Student;
import com.koichi.assignment8.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    /**
     * INSERT用のController
     */

    @PostMapping("/students")
    public ResponseEntity<StudentResponse> insert(@RequestBody @Validated StudentRequest studentRequest, UriComponentsBuilder uriBuilder) {
        Student student = studentService.insert(studentRequest.getName(), studentRequest.getGrade(), studentRequest.getBirthPlace());
        URI location = uriBuilder.path("/students/{id}").buildAndExpand(student.getId()).toUri();
        StudentResponse body = new StudentResponse("student created");
        return ResponseEntity.created(location).body(body);
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable("id") Integer id, @RequestBody @Validated StudentUpdateRequest studentUpdateRequest) {
        studentService.updateStudent(id, studentUpdateRequest.getName(), studentUpdateRequest.getGrade(), studentUpdateRequest.getBirthPlace());
        StudentResponse body = new StudentResponse("Student updated");
        return ResponseEntity.status(HttpStatus.OK).body(body);

    }


}
