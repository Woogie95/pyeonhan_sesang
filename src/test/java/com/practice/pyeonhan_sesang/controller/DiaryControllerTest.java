package com.practice.pyeonhan_sesang.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.pyeonhan_sesang.dto.request.CreateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.response.CreateDiaryResponse;
import com.practice.pyeonhan_sesang.dto.response.DiaryResponse;
import com.practice.pyeonhan_sesang.service.DiaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DiaryController.class)
class DiaryControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiaryService diaryService;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new DiaryController(diaryService)).build();
    }

    @Test
    @DisplayName("글 등록 테스트")
    void createDiary() throws Exception {
        // given
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest("최성욱", "인삿말", "안녕하세요.",
                "맑음", LocalDateTime.now(), LocalDateTime.now());
        CreateDiaryResponse createDiaryResponse = new CreateDiaryResponse(
                2L, "최성욱", "인삿말", "안녕하세요.", "맑음", LocalDateTime.now(), LocalDateTime.now());
        given(diaryService.createDiary(any())).willReturn(createDiaryResponse);

        //when
        String valueAsString = objectMapper.writeValueAsString(createDiaryRequest);

        //then
        mockMvc.perform(post("/diaries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(valueAsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.author").value("최성욱"))
                .andExpect(jsonPath("$.title").value("인삿말"))
                .andExpect(jsonPath("$.content").value("안녕하세요."))
                .andExpect(jsonPath("$.weather").value("맑음"))
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andDo(print());
    }

    @Test
    void getAllDiaries() {
    }

    @Test
    @DisplayName("글 상세 조회 테스트")
    void getDiaryDetail() throws Exception {
        // given
        Long diaryId = 1L;
        DiaryResponse diaryResponse = new DiaryResponse(1L, "최성욱", "제목", "내용", "날씨", LocalDateTime.now(), LocalDateTime.now());
        given(diaryService.getDetailDiary(diaryId)).willReturn(diaryResponse);

        // when
        mockMvc.perform(get("/diaries/{id}", diaryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(diaryResponse.getId()))
                .andExpect(jsonPath("$.author").value(diaryResponse.getAuthor()))
                .andExpect(jsonPath("$.title").value(diaryResponse.getTitle()))
                .andExpect(jsonPath("$.content").value(diaryResponse.getContent()))
                .andExpect(jsonPath("$.weather").value(diaryResponse.getWeather()))
                .andExpect(jsonPath("$.created_at").value(diaryResponse.getCreated_at().toString()))
                .andExpect(jsonPath("$.updated_at").value(diaryResponse.getUpdated_at().toString()))
                .andDo(print());

        // then
        verify(diaryService).getDetailDiary(diaryId);
    }

    @Test
    void updateDiary() {
    }

    @Test
    void deleteDiary() {
    }
}