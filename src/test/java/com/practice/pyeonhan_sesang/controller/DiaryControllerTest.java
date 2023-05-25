package com.practice.pyeonhan_sesang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.pyeonhan_sesang.dto.request.CreateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.response.CreateDiaryResponse;
import com.practice.pyeonhan_sesang.entity.Diary;
import com.practice.pyeonhan_sesang.service.DiaryService;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DiaryControllerTest.class)
class DiaryControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiaryService diaryService; // MemberService 의 Mock 객체를 선언, 이 Mock 객체는 DiaryController 에 주입되어 테스트 중에 사용

    @BeforeEach
    public void setUp() { // @BeforeEach 애너테이션이 메서드 위에 선언되어 있으므로, setUp() 메서드는 각 테스트 메소드 실행 전에 호출
        // 1. MockMvc 객체를 선언합니다. 이 객체는 스프링 MVC 테스트를 수행하는 데 사용됩니다.
        mockMvc = MockMvcBuilders.standaloneSetup(new DiaryController(diaryService))
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) // utf-8 필터 추가
                .build();
    }

    @Test
    @DisplayName("글 등록 테스트")
    void createDiary() throws Exception {
        // given

//        CreateDiaryResponse createDiary이걸호출하면나는이걸리턴할것이다 = new CreateDiaryResponse(
//                1L,
//                "ㅎㅇ",
//                "나는 전두환",
//                "앙 기모찌",
//                "좋음",
//                LocalDateTime.now(),
//                LocalDateTime.now());
//        given(diaryService.createDiary(any())).willReturn(createDiary이걸호출하면나는이걸리턴할것이다);
        final LocalDateTime now = LocalDateTime.now();
        given(diaryService.createDiary(any()))
                .willReturn(
                        CreateDiaryResponse.from(Diary.builder()
                                .id(1L)
                                .author("최성욱")
                                .title("인삿말")
                                .content("안녕하세요")
                                .weather("맑음")
                                .created_at(LocalDateTime.now())
                                .updated_at(LocalDateTime.now())
                                .build()));


        // when
        final ResultActions actions =
                mockMvc.perform(
                        post("/diaries")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .characterEncoding("UTF-8")
                                .content(
                                        "{"
                                                + "  \"author\" : \"최성욱\", "
                                                + "  \"title\" : \"인삿말\", "
                                                + "  \"content\": \"안녕하세요\", "
                                                + "  \"weather\": \"맑음\" "
                                                + "}"));
        // then
        actions
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("author").value("최성욱"))
                .andExpect((ResultMatcher) jsonPath("title").value("인삿말"))
                .andExpect((ResultMatcher) jsonPath("content").value("안녕하세요"))
                .andExpect((ResultMatcher) jsonPath("weather").value("맑음"));

    }

    @Test
    void getAllDiaries() {
    }

    @Test
    void getDiaryDetail() {
    }

    @Test
    void updateDiary() {
    }

    @Test
    void deleteDiary() {
    }
}