package com.practice.pyeonhan_sesang.controller;

import com.practice.pyeonhan_sesang.dto.request.DiaryInsertRequest;
import com.practice.pyeonhan_sesang.dto.request.DiaryUpdateRequest;
import com.practice.pyeonhan_sesang.dto.response.DiaryInsertResponse;
import com.practice.pyeonhan_sesang.dto.response.DiaryUpdateResponse;
import com.practice.pyeonhan_sesang.service.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
@AllArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    // 등록
    @PostMapping("/insert")
    public DiaryInsertResponse insertDiary(@RequestBody DiaryInsertRequest diaryInsertRequest) {
        return diaryService.insertDiary(diaryInsertRequest);
    }

    // 전체 조회
    @GetMapping("/diaries")
    public List<DiaryInsertResponse> getAllDiaries() {
        return diaryService.getAllDiaries();
    }

    // 상세조회
    @GetMapping("/diaries/{id}")
    public DiaryInsertResponse getDiary(@PathVariable Long id) {
        return diaryService.getDiary(id);
    }

    // 수정
    @PutMapping("/{id}")
    public DiaryUpdateResponse updateDiary(@PathVariable Long id, @RequestBody DiaryUpdateRequest diaryUpdateRequest) throws ChangeSetPersister.NotFoundException {
        return diaryService.updateDiary(id, diaryUpdateRequest);
    }


    // 삭제
    @DeleteMapping("/{id}")
    public void deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);
    }

}

