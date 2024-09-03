package com.koichi.assignment8.itegrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

    @ParameterizedTest(name = "{2}")
    @CsvSource({
            "'/students/999','{\"error\":\"Not Found\",\"timestamp\":\"2024/01/01 T00:00:00+0900［Asia/Tokyo］\",\"message\":\"student not found\",\"status\":\"404\",\"path\":\"/students/999\"}',存在しない学生を取得する際にhandleUserNotFoundExceptionを返す",
            "'/students/あ','{\"error\":\"Bad Request\",\"timestamp\":\"2024/01/01 T00:00:00+0900［Asia/Tokyo］\",\"message\":\"IDは数字で入力してください\",\"status\":\"400\",\"path\":\"/students/%E3%81%82\"}',IDが文字列の場合にhandleMethodArgumentTypeMismatchExceptionを返す",
            "'/students/ ','{\"error\":\"Bad Request\",\"timestamp\":\"2024/01/01 T00:00:00+0900［Asia/Tokyo］\",\"message\":\"IDは数字で入力してください\",\"status\":\"400\",\"path\":\"/students/%20\"}',IDが空白の場合にhandleMethodArgumentTypeMismatchExceptionを返す"
    })
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void IDに該当する学生を取得する際の例外処理のレスポンスを返すこと(String requestPath, String response, String testName) throws Exception {

        final ZonedDateTime fixedClock = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

        try (MockedStatic<ZonedDateTime> mockClock = Mockito.mockStatic(ZonedDateTime.class)) {

            mockClock.when(ZonedDateTime::now).thenReturn(fixedClock);

            mockMvc.perform(MockMvcRequestBuilders.get(requestPath))
                    .andExpect(MockMvcResultMatchers.content().json(response));
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

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void 一年生の学生をクエリパラメータの検索を使用して取得すること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students?grade=1"))
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
                            }
                        ]
                         """));
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void 人名の頭文字が溝である学生をクエリパラメータの検索を使用して複数取得すること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students?startsWith=溝"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        [
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
                            }
                        ]
                         """));
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void 大分県出身の学生をクエリパラメータの検索を使用して取得すること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students?birthPlace=大分県"))
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
                                "id": 3,
                                "name": "岡崎徹",
                                "grade": "二年生",
                                "birthPlace": "大分県"
                            }
                        ]
                         """));
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void 実際にないカラムでクエリパラメータの検索を使用して取得する際に全ての学生を取得すること() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students?gender=男性"))
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

    @ParameterizedTest(name = "{2}")
    @CsvSource({
            "/students?grade=1&birthPlace=大分県,'{\"message\": \"カラムはgrade・startsWith・birthPlaceの一つを選んでください\",\"status\": \"400\", \"path\": \"/students\", \"error\": \"Bad Request\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\"}',複数のカラムで検索した場合handleMultipleMethodsExceptionを返す",
            "/students?grade=一年生,'{\"path\": \"/students\", \"status\": \"400\", \"message\": \"学年は半角数字で入力してください\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"error\": \"Bad Request\"}',学年での検索時に文字列を入力した場合handleMethodArgumentTypeMismatchExceptionを返す",
            "/students?startsWith=阿,[],実際にいない人名の頭文字でクエリパラメータの検索をしたらEmptyを返す",
            "/students?birthPlace=大阪府,[],実際にいない出身地でクエリパラメータの検索を使用したらEmptyを返す"
    })
    @DataSet(value = "datasets/students.yml")
    @Transactional
    void クエリパラメータの検索の際の例外処理のレスポンスを返却すること(String path, String response, String testName) throws Exception {

        final ZonedDateTime fixedClock = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

        try (MockedStatic<ZonedDateTime> mockClock = Mockito.mockStatic(ZonedDateTime.class)) {
            mockClock.when(ZonedDateTime::now).thenReturn(fixedClock);
            mockMvc.perform(MockMvcRequestBuilders.get(path))
                    .andExpect(MockMvcResultMatchers.content().json(response));
        }
    }


    @Test
    @DataSet(value = "datasets/students.yml")
    @ExpectedDataSet(value = "datasets/studentsToRegister.yml", ignoreCols = "id")
    @Transactional
    void 新しい学生を登録すること() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name":"中田健太",
                                    "grade":"一年生",
                                    "birthPlace":"福岡県"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "message": "student created"
                         }
                        """));

    }

    @ParameterizedTest(name = "{3}")
    @CsvSource({
            "/students,'{\"name\":\"\" ,\"grade\":\"一年生\",\"birthPlace\":\"福岡県\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"name\", \"message\": \"nameを入力してください\"}]}',学生を登録する際に名前が空白の場合、handleMethodArgumentNotValidExceptionを返す",
            "/students,'{\"name\":\"中田健太\" ,\"grade\":\"\",\"birthPlace\":\"福岡県\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"grade\", \"message\": \"有効な学年を指定してください（一年生, 二年生, 三年生のいずれか）。\"}]}',学生を登録する際に学年が空白の場合、handleMethodArgumentNotValidExceptionを返す",
            "/students,'{\"name\":\"中田健太\" ,\"grade\":\"あ\",\"birthPlace\":\"福岡県\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"grade\", \"message\": \"有効な学年を指定してください（一年生, 二年生, 三年生のいずれか）。\"}]}',学生を登録する際に学年が文字列の場合、handleMethodArgumentNotValidExceptionを返す",
            "/students,'{\"name\":\"中田健太\" ,\"grade\":\"一年生\",\"birthPlace\":\"\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"birthPlace\", \"message\": \"birthPlaceを入力してください\"}]}',学生を登録する際に出身地が空白の場合、handleMethodArgumentNotValidExceptionを返す",
            "/students,'{\"name\":\"\" ,\"grade\":\"\",\"birthPlace\":\"\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"name\", \"message\": \"nameを入力してください\"},{\"field\": \"grade\", \"message\": \"有効な学年を指定してください（一年生, 二年生, 三年生のいずれか）。\"},{\"field\": \"birthPlace\", \"message\": \"birthPlaceを入力してください\"}]}',学生を登録する際に全てのカラムがない場合、handleMethodArgumentNotValidExceptionを返す"
    })
    @DataSet(value = "datasets/students.yml")
    @ExpectedDataSet(value = "datasets/students.yml")
    @Transactional
    void 新しい学生を登録する際の例外処理のレスポンスを返却すること(String path, String requestBody, String response, String testName) throws Exception {

        final ZonedDateTime fixedClock = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

        try (MockedStatic<ZonedDateTime> mockClock = Mockito.mockStatic(ZonedDateTime.class)) {
            mockClock.when(ZonedDateTime::now).thenReturn(fixedClock);

            mockMvc.perform(MockMvcRequestBuilders.post(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.content().json(response));
        }
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @ExpectedDataSet(value = "datasets/studentsToRenewing.yml", ignoreCols = "id")
    @Transactional
    void IDに該当する学生のデータを更新出来ること() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name":"城野健一",
                                    "grade":"二年生",
                                    "birthPlace":"福岡県"
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                             "message": "Student updated"
                        }
                        """));
    }

    @ParameterizedTest(name = "{3}")
    @CsvSource({
            "/students/0,'{\"name\":\"城野健一\",\"grade\":\"二年生\", \"birthPlace\":\"福岡県\"}','{ \"path\": \"/students/0\", \"status\": \"404\", \"message\": \"student not found\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"error\": \"Not Found\"}',指定したIDの学生がいない場合に、handleUserNotFoundExceptionを返す",
            "/students/あ,'{\"name\":\"城野健一\",\"grade\":\"二年生\", \"birthPlace\":\"福岡県\"}','{ \"path\": \"/students/%E3%81%82\", \"status\": \"400\", \"message\": \"IDは数字で入力してください\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"error\": \"Bad Request\"}',指定したIDが文字列の場合にhandleMethodArgumentTypeMismatchExceptionを返す",
            "'/students/ ','{\"name\":\"城野健一\",\"grade\":\"二年生\", \"birthPlace\":\"福岡県\"}','{ \"path\": \"/students/%20\", \"status\": \"400\", \"message\": \"IDは数字で入力してください\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"error\": \"Bad Request\"}',指定したIDが空白の場合にhandleMethodArgumentTypeMismatchExceptionを返す",
            "/students,'{\"name\":\"城野健一\",\"grade\":\"二年生\", \"birthPlace\":\"福岡県\"}','{ \"path\": \"/students\", \"status\": \"400\", \"message\": \"学生のIDを入力してください\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"error\": \"Bad Request\"}',指定したIDの学生のデータを更新する際に全学生が指定されている場合、handleHttpRequestMethodNotSupportedExceptionを返す",
            "/students/1,'{\"name\":\"\" ,\"grade\":\"一年生\",\"birthPlace\":\"福岡県\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"name\", \"message\": \"nameを入力してください\"}]}',指定したIDの学生のデータを更新する際に名前が空白の場合、handleMethodArgumentNotValidExceptionを返す",
            "/students/1,'{\"name\":\"中田健太\" ,\"grade\":\"\",\"birthPlace\":\"福岡県\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"grade\", \"message\": \"有効な学年を指定してください（一年生, 二年生, 三年生,卒業生のいずれか）。\"}]}',指定したIDの学生のデータを更新する際に学年が空白の場合、handleMethodArgumentNotValidExceptionを返す",
            "/students/1,'{\"name\":\"中田健太\" ,\"grade\":\"あ\",\"birthPlace\":\"福岡県\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"grade\", \"message\": \"有効な学年を指定してください（一年生, 二年生, 三年生,卒業生のいずれか）。\"}]}',指定したIDの学生のデータを更新する際に学年が文字列の場合、handleMethodArgumentNotValidExceptionを返す",
            "/students/1,'{\"name\":\"中田健太\" ,\"grade\":\"一年生\",\"birthPlace\":\"\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"birthPlace\", \"message\": \"birthPlaceを入力してください\"}]}',指定したIDの学生のデータを更新する際に出身地が空白の場合、handleMethodArgumentNotValidExceptionを返す",
            "/students/1,'{\"name\":\"\" ,\"grade\":\"\",\"birthPlace\":\"\"}','{ \"status\": \"400\", \"message\": \"validation error\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\", \"errors\": [{\"field\": \"name\", \"message\": \"nameを入力してください\"},{\"field\": \"grade\", \"message\": \"有効な学年を指定してください（一年生, 二年生, 三年生,卒業生のいずれか）。\"},{\"field\": \"birthPlace\", \"message\": \"birthPlaceを入力してください\"}]}',指定したIDの学生のデータを更新する際に全てのカラムがない場合、handleMethodArgumentNotValidExceptionを返す"
    })
    @DataSet(value = "datasets/students.yml")
    @ExpectedDataSet(value = "datasets/students.yml")
    @Transactional
    void 指定したIDの学生のデータを更新する際の例外処理のレスポンスが返却されること(String path, String request, String response, String testName) throws Exception {

        final ZonedDateTime fixedClock = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

        try (MockedStatic<ZonedDateTime> mockClock = Mockito.mockStatic(ZonedDateTime.class)) {
            mockClock.when(ZonedDateTime::now).thenReturn(fixedClock);

            mockMvc.perform(MockMvcRequestBuilders.patch(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(MockMvcResultMatchers.content().json(response));
        }
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @ExpectedDataSet(value = "datasets/gradeAdvancement.yml", ignoreCols = "id")
    @Transactional
    void 学生の学年を進級させること() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/students/grade/_batchUpdate"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                             "message": "Grade updated"
                        }
                        """));
    }

    @Test
    @DataSet(value = "datasets/students.yml")
    @ExpectedDataSet(value = "datasets/studentsToRemoved.yml")
    @Transactional
    void IDに該当する学生のデータを削除出来ること() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/students/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "message": "Student deleted"
                        }
                        """));
    }

    @ParameterizedTest(name = "{2}")
    @CsvSource({
            "'/students/999','{\"error\":\"Not Found\",\"timestamp\":\"2024/01/01 T00:00:00+0900［Asia/Tokyo］\",\"message\":\"student not found\",\"status\":\"404\",\"path\":\"/students/999\"}',存在しない学生を削除する際にhandleUserNotFoundExceptionを返す",
            "'/students/あ','{\"error\":\"Bad Request\",\"timestamp\":\"2024/01/01 T00:00:00+0900［Asia/Tokyo］\",\"message\":\"IDは数字で入力してください\",\"status\":\"400\",\"path\":\"/students/%E3%81%82\"}',指定したIDが文字列の場合にhandleMethodArgumentTypeMismatchExceptionを返す",
            "'/students/ ','{\"error\":\"Bad Request\",\"timestamp\":\"2024/01/01 T00:00:00+0900［Asia/Tokyo］\",\"message\":\"IDは数字で入力してください\",\"status\":\"400\",\"path\":\"/students/%20\"}',指定したIDが空白の場合にhandleMethodArgumentTypeMismatchExceptionを返す",
            "/students,'{\"status\": \"400\",\"path\": \"/students\",\"error\": \"Bad Request\", \"timestamp\": \"2024/01/01 T00:00:00+0900［Asia/Tokyo］\",\"message\": \"学生のIDを入力してください\" }',指定したIDの学生のデータを削除する際に全学生が指定されている場合、handleHttpRequestMethodNotSupportedExceptionを返す"
    })
    @DataSet(value = "datasets/students.yml")
    @ExpectedDataSet(value = "datasets/students.yml")
    @Transactional
    void IDに該当する学生のデータを削除する際の例外処理のレスポンスを返却すること(String path, String response, String testName) throws Exception {

        final ZonedDateTime fixedClock = ZonedDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneId.of("Asia/Tokyo"));

        try (MockedStatic<ZonedDateTime> mockClock = Mockito.mockStatic(ZonedDateTime.class)) {
            mockClock.when(ZonedDateTime::now).thenReturn(fixedClock);

            mockMvc.perform(MockMvcRequestBuilders.delete(path))
                    .andExpect(MockMvcResultMatchers.content().json(response));
        }
    }
}
