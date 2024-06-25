package com.koichi.assignment8.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.koichi.assignment8.entity.Student;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    public void 全ての学生を取得すること() {

        List<Student> findAllStudents = studentMapper.findAllStudents();
        assertThat(findAllStudents).contains(
                new Student(1, "清⽔圭吾", "一年生", "大分県"),
                new Student(2, "田中圭", "一年生", "福岡県"),
                new Student(3, "岡崎徹", "二年生", "大分県"),
                new Student(4, "溝口光一", "二年生", "熊本県"),
                new Student(5, "溝谷望", "三年生", "熊本県"),
                new Student(6, "安藤孝弘", "三年生", "福岡県")
        );
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    public void 一年生の学生をクエリパラメータの検索を使用して取得すること() {

        List<Student> findByGrade = studentMapper.findByGrade("一年生");
        assertThat(findByGrade).contains(
                new Student(1, "清⽔圭吾", "一年生", "大分県"),
                new Student(2, "田中圭", "一年生", "福岡県")
        );
    }
}
