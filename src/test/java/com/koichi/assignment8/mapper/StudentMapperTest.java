package com.koichi.assignment8.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.koichi.assignment8.entity.Student;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentMapperTest {

    @Autowired
    StudentMapper studentMapper;

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    public void IDに該当する学生が一件取得できること() {

        Optional<Student> findById = studentMapper.findById(1);
        assertThat(findById).contains(
                new Student(1, "清⽔圭吾", "一年生", "大分県")
        );
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    public void IDに該当する学生がいない場合空のOptionalを返すこと() {

        Optional<Student> findById = studentMapper.findById(999);
        assertThat(findById).isEmpty();
    }

}
