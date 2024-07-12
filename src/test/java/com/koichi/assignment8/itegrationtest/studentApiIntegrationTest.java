package com.koichi.assignment8.itegrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

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

        final ZonedDateTime fixedClock = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

        try (MockedStatic<ZonedDateTime> mockClock = Mockito.mockStatic(ZonedDateTime.class)) {
            mockClock.when(ZonedDateTime::now).thenReturn(fixedClock);
            mockMvc.perform(MockMvcRequestBuilders.get("/students/999"))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andExpect(MockMvcResultMatchers.content().json("""
                            {
                              "error": "Not Found",
                              "path": "/students/999",
                              "status": "404",
                              "timestamp": "2024/01/01 T00:00:00+0900［Asia/Tokyo］",
                              "message": "student not found"
                            }                                                      
                            """));
        }
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void 全ての学生を取得すること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
                           {
                             "id": 1,
                             "name": "清⽔圭吾",
                             "grade": "一年生",
                             "birthPlace": "大分県"
                           },
                           {
                             "id": 2,
                             "name": "田中圭",
                             "grade": "一年生",
                             "birthPlace": "福岡県"
                           },
                           {
                             "id": 3,
                             "name": "岡崎徹",
                             "grade": "二年生",
                             "birthPlace": "大分県"
                           },
                           {
                             "id": 4,
                             "name": "溝口光一",
                             "grade": "二年生",
                             "birthPlace": "熊本県"
                           },
                           {
                             "id": 5,
                             "name": "溝谷望",
                             "grade": "三年生",
                             "birthPlace": "熊本県"
                           },
                           {
                             "id": 6,
                             "name": "安藤孝弘",
                             "grade": "三年生",
                             "birthPlace": "福岡県"
                           }
                        ]
                         """));
    }

}

