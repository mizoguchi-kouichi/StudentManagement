package com.koichi.assignment8.service;

import com.koichi.assignment8.entity.Student;
import com.koichi.assignment8.excption.StudentNotFoundException;
import com.koichi.assignment8.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentMapper studentMapper;

    @Test
    public void IDに該当する学生が一件取得できること() {
        doReturn(Optional.of(new Student(1, "溝口光一", "一年生", "大分県"))).when(studentMapper).findById(1);
        Student actual = studentService.findStudent(1);
        assertThat(actual).isEqualTo(new Student(1, "溝口光一", "一年生", "大分県"));
    }

    @Test
    public void IDに該当する学生がいない時にstudentnotfoundというメッセージが返却されること() {
        doReturn(Optional.empty()).when(studentMapper).findById(1);
        assertThatThrownBy(() -> studentService.findStudent(1))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("student not found");
    }

    @Test
    public void 全ての学生を取得すること() {
        List<Student> findAllStudents = List.of(new Student(1, "溝口光一", "一年生", "大分県"),
                new Student(2, "中野乃蒼", "二年生", "福岡県"),
                new Student(3, "安藤健", "三年生", "熊本県"));
        doReturn(findAllStudents).when(studentMapper).findAllStudents();
        List<Student> actualList = studentService.findAllStudents(null, null, null);
        assertThat(actualList).isEqualTo(findAllStudents);
    }

    @Test
    public void 一年生の学生をクエリパラメータの検索を使用して取得すること() {
        List<Student> getByGrade = List.of(new Student(1, "溝口光一", "一年生", "大分県"));
        doReturn(getByGrade).when(studentMapper).findByGrade("一年生");
        List<Student> actualList = studentService.findAllStudents(1, null, null);
        assertThat(actualList).isEqualTo(getByGrade);
    }
}