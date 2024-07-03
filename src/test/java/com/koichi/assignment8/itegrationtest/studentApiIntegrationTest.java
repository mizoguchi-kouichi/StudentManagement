package com.koichi.assignment8.itegrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class studentApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void IDに該当する学生が一件取得できること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                          "id": 1,
                          "name": "清⽔圭吾",
                          "grade": "一年生",
                          "birthPlace": "大分県"
                        }                                                          
                        """));
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void IDに該当する学生がいない時にStudentNotFoundExceptionのレスポンスボティが返却されること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/999"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                          "error": "Not Found",
                          "path": "/students/999",
                          "status": "404",
                          "message": "student not found"
                        }                                                      
                        """));
    }

}
