package com.practice.pyeonhan_sesang.controller;

import com.practice.pyeonhan_sesang.dto.request.DiaryRequest;
import com.practice.pyeonhan_sesang.dto.response.DiaryResponse;
import com.practice.pyeonhan_sesang.service.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
@AllArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    // 등록
    @PostMapping("/insert")
    public DiaryResponse insertDiary(@RequestBody DiaryRequest diaryRequest) {
        return diaryService.insertDiary(diaryRequest);
    }

    // 전체 조회
    @GetMapping("/diaries")
    public List<DiaryResponse> getAllDiaries() {
        return diaryService.getAllDiaries();
    }

//    // 상세조회
//    @GetMapping("/diaries/{id}")
//    public DiaryResponse getDiary(@PathVariable Long id) {
//
//    }

    // 수정

    // 삭제
    @DeleteMapping("/Diaries/{id}")
    public void deleteDiary(@PathVariable Long id) {

    }
}

