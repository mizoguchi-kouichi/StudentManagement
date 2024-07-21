package com.koichi.assignment8.controller;

import com.koichi.assignment8.controller.request.StudentPostRequest;
import com.koichi.assignment8.controller.request.StudentUpdateRequest;
import com.koichi.assignment8.controller.response.StudentResponse;
import com.koichi.assignment8.entity.Student;
import com.koichi.assignment8.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * SELECT用のController
     * 指定したidのstudentのデータを全て取得します。
     */
    @GetMapping("/students/{id}")
    public Student findById(@PathVariable("id") Integer id) {
        return studentService.findStudent(id);
    }

    /**
     * SELECT用のController
     * 指定した検索パラメータに一致するstudentのデータを取得します。
     */
    @GetMapping("/students")
    public List<Student> getStudents(@RequestParam(required = false) Integer grade, String startsWith, String birthPlace) {
        return studentService.findAllStudents(grade, startsWith, birthPlace);
    }


    /**
     * INSERT用のController
     */
    @PostMapping("/students")
    public ResponseEntity<StudentResponse> insertStudent(@RequestBody @Validated StudentPostRequest studentPostRequest, UriComponentsBuilder uriBuilder) {
        Student student = studentService.insertStudent(studentPostRequest.getName(), studentPostRequest.getGrade(), studentPostRequest.getBirthPlace());
        URI location = uriBuilder.path("/students/{id}").buildAndExpand(student.getId()).toUri();
        StudentResponse body = new StudentResponse("student created");
        return ResponseEntity.created(location).body(body);
    }

    /**
     * PATCH用のController
     * 指定したidのstudentの name,grade,birthplaceを更新します。
     */
    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable("id") Integer id, @RequestBody @Validated StudentUpdateRequest studentUpdateRequest) {
        studentService.updateStudent(id, studentUpdateRequest.getName(), studentUpdateRequest.getGrade(), studentUpdateRequest.getBirthPlace());
        StudentResponse body = new StudentResponse("Student updated");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    /**
     * PATCH用のController
     * 指定したgradeを進級します。
     */
    @PatchMapping("/students/grade:batchUpdate")
    public ResponseEntity<StudentResponse> updateGrade() {
        studentService.updateGrade();
        StudentResponse body = new StudentResponse("Grade updated");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    /**
     * DELETE用のController
     * 指定したidのstudentのデータを削除します。
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<StudentResponse> deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteStudent(id);
        StudentResponse body = new StudentResponse("Student deleted");
        return ResponseEntity.ok(body);
    }
}
