package com.practice.pyeonhan_sesang.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.pyeonhan_sesang.dto.request.CreateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.request.UpdateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.response.CreateDiaryResponse;
import com.practice.pyeonhan_sesang.dto.response.DiaryResponse;
import com.practice.pyeonhan_sesang.dto.response.UpdateDiaryResponse;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    @DisplayName("글 등록")
    void createDiary() throws Exception {
        // given
        CreateDiaryRequest createDiaryRequest = new CreateDiaryRequest("최성욱", "인삿말", "안녕하세요.",
                "맑음", LocalDateTime.now(), LocalDateTime.now());
        CreateDiaryResponse createDiaryResponse = new CreateDiaryResponse(
                2L, "최성욱", "인삿말", "안녕하세요.", "맑음", LocalDateTime.now(), LocalDateTime.now());
        given(diaryService.createDiary(any())).willReturn(createDiaryResponse);

        // when & then
        String valueAsString = objectMapper.writeValueAsString(createDiaryRequest);
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
    @DisplayName("글 전체 조회")
    void getAllDiaries() throws Exception {
        // given
        List<DiaryResponse> diaries = List.of(
                new DiaryResponse(1L, "최성욱", "제목1", "내용1", "날씨1", LocalDateTime.now(), LocalDateTime.now()),
                new DiaryResponse(2L, "홍길동", "제목2", "내용2", "날씨2", LocalDateTime.now(), LocalDateTime.now())
        );
        given(diaryService.findAll()).willReturn(diaries);

        // when & then
        mockMvc.perform(get("/diaries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(diaries.size()))
                .andExpect(jsonPath("$[0].id").value(diaries.get(0).getId()))
                .andExpect(jsonPath("$[0].author").value(diaries.get(0).getAuthor()))
                .andExpect(jsonPath("$[0].title").value(diaries.get(0).getTitle()))
                .andExpect(jsonPath("$[0].content").value(diaries.get(0).getContent()))
                .andExpect(jsonPath("$[0].weather").value(diaries.get(0).getWeather()))
                .andExpect(jsonPath("$[0].created_at").exists())
                .andExpect(jsonPath("$[0].updated_at").exists())
                .andExpect(jsonPath("$[1].id").value(diaries.get(1).getId()))
                .andExpect(jsonPath("$[1].author").value(diaries.get(1).getAuthor()))
                .andExpect(jsonPath("$[1].title").value(diaries.get(1).getTitle()))
                .andExpect(jsonPath("$[1].content").value(diaries.get(1).getContent()))
                .andExpect(jsonPath("$[1].weather").value(diaries.get(1).getWeather()))
                .andExpect(jsonPath("$[1].created_at").exists())
                .andExpect(jsonPath("$[1].updated_at").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("글 상세 조회")
    void getDiaryDetail() throws Exception {
        // given
        Long diaryId = 1L;
        DiaryResponse diaryResponse = new DiaryResponse(1L, "최성욱", "제목", "내용", "날씨", LocalDateTime.now(), LocalDateTime.now());
        given(diaryService.findById(diaryId)).willReturn(diaryResponse);

        // when & then
        mockMvc.perform(get("/diaries/{id}", diaryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.author").value("최성욱"))
                .andExpect(jsonPath("$.title").value("제목"))
                .andExpect(jsonPath("$.content").value("내용"))
                .andExpect(jsonPath("$.weather").value("날씨"))
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("전체 수정")
    void updateDiary() throws Exception {
        // given
        Long diaryId = 1L;
        UpdateDiaryRequest updateRequest = new UpdateDiaryRequest("새로운 제목", "새로운 내용",
                "새로운 날씨", LocalDateTime.now());
        UpdateDiaryResponse updatedDiaryResponse = new UpdateDiaryResponse(diaryId, "강감찬", "새로운 제목",
                "새로운 내용", "새로운 날씨", LocalDateTime.now(), LocalDateTime.now());
        given(diaryService.updateDiary(any(), any())).willReturn(updatedDiaryResponse);
        // when & then
        String writeValueAsString = objectMapper.writeValueAsString(updateRequest);
        mockMvc.perform(put("/diaries/{id}", diaryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.author").value("강감찬"))
                .andExpect(jsonPath("$.title").value("새로운 제목"))
                .andExpect(jsonPath("$.content").value("새로운 내용"))
                .andExpect(jsonPath("$.weather").value("새로운 날씨"))
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("제목 수정")
    void updateTitleDiary() throws Exception {
        // given
        Long diaryId = 1L;
        UpdateDiaryRequest updateDiaryRequest = new UpdateDiaryRequest("새로운 제목", "기존 내용",
                "기존 날씨", LocalDateTime.now());
        UpdateDiaryResponse updateDiaryResponse = new UpdateDiaryResponse(diaryId, "최성욱", "새로운 제목",
                "기존 내용", "기존 날씨", LocalDateTime.now(), LocalDateTime.now());
        given(diaryService.updateDiary(any(), any())).willReturn(updateDiaryResponse);
        //when & than
        String writeValueAsString = objectMapper.writeValueAsString(updateDiaryRequest);
        mockMvc.perform(put("/diaries/{id}", diaryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.author").value("최성욱"))
                .andExpect(jsonPath("$.title").value("새로운 제목"))
                .andExpect(jsonPath("$.content").value("기존 내용"))
                .andExpect(jsonPath("$.weather").value("기존 날씨"))
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("내용 수정")
    void updateContentDiary() throws Exception {
        // given
        Long diaryId = 2L;
        UpdateDiaryRequest updateDiaryRequest = new UpdateDiaryRequest("기존 제목", "새로운 내용",
                "기존 날씨", LocalDateTime.now());
        UpdateDiaryResponse updateDiaryResponse = new UpdateDiaryResponse(diaryId, "최성욱", "기존 제목",
                "새로운 내용", "기존 날씨", LocalDateTime.now(), LocalDateTime.now());
        given(diaryService.updateDiary(any(), any())).willReturn(updateDiaryResponse);
        //when & than
        String writeValueAsString = objectMapper.writeValueAsString(updateDiaryRequest);
        mockMvc.perform(put("/diaries/{id}", diaryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.author").value("최성욱"))
                .andExpect(jsonPath("$.title").value("기존 제목"))
                .andExpect(jsonPath("$.content").value("새로운 내용"))
                .andExpect(jsonPath("$.weather").value("기존 날씨"))
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("날씨 수정")
    void updateWeatherDiary() throws Exception {
        // given
        Long diaryId = 3L;
        UpdateDiaryRequest updateDiaryRequest = new UpdateDiaryRequest("기존 제목", "기존 내용",
                "새로운 날씨", LocalDateTime.now());
        UpdateDiaryResponse updateDiaryResponse = new UpdateDiaryResponse(diaryId, "최성욱", "기존 제목",
                "기존 내용", "새로운 날씨", LocalDateTime.now(), LocalDateTime.now());
        given(diaryService.updateDiary(any(), any())).willReturn(updateDiaryResponse);
        //when & than
        String writeValueAsString = objectMapper.writeValueAsString(updateDiaryRequest);
        mockMvc.perform(put("/diaries/{id}", diaryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.author").value("최성욱"))
                .andExpect(jsonPath("$.title").value("기존 제목"))
                .andExpect(jsonPath("$.content").value("기존 내용"))
                .andExpect(jsonPath("$.weather").value("새로운 날씨"))
                .andExpect(jsonPath("$.created_at").exists())
                .andExpect(jsonPath("$.updated_at").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("글 삭제")
    void deleteDiary() throws Exception {
        // given
        Long diaryId = 1L;
        // when & then
        mockMvc.perform(delete("/diaries/{id}", diaryId))
                .andExpect(status().isOk())
                .andDo(print());
        verify(diaryService).deleteDiary(diaryId);
    }

}