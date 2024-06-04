package com.koichi.assignment8.service;

import com.koichi.assignment8.entity.Student;
import com.koichi.assignment8.excption.StudentNotFoundException;
import com.koichi.assignment8.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    public void 存在する学生のIDをしてしたときに正常にユーザーが返される事() {
        doReturn(Optional.of(new Student(1, "溝口光一", "一年生", "大分県"))).when(studentMapper).findById(1);
        Student actual = studentService.findStudent(1);
        assertThat(actual).isEqualTo(new Student(1, "溝口光一", "一年生", "大分県"));

    }

    @Test
    public void 指定したIDの学生がいなかったときに例外処理が返される事() {
        doReturn(Optional.empty()).when(studentMapper).findById(1);
        assertThatThrownBy(() -> studentService.findStudent(1))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessage("student not found");
    }
}