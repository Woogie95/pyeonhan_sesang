package com.practice.pyeonhan_sesang.controller;

import com.practice.pyeonhan_sesang.dto.request.CreateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.request.UpdateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.response.DiaryResponse;
import com.practice.pyeonhan_sesang.dto.response.CreateDiaryResponse;
import com.practice.pyeonhan_sesang.dto.response.UpdateDiaryResponse;
import com.practice.pyeonhan_sesang.service.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diaries")
@AllArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    // 등록
    @PostMapping()
    public CreateDiaryResponse createDiary(@RequestBody CreateDiaryRequest createDiaryRequest) {
        return diaryService.createDiary(createDiaryRequest);
    }

    // 전체 조회
    @GetMapping()
    public List<DiaryResponse> getAllDiaries() {
        return diaryService.findAll();
    }

    // 상세조회
    @GetMapping("/{id}")
    public DiaryResponse getDiaryDetail(@PathVariable Long id) {
        return diaryService.findById(id);
    }

    // 수정
    @PutMapping("/{id}")
    public UpdateDiaryResponse updateDiary(@PathVariable Long id, @RequestBody UpdateDiaryRequest updateDiaryRequest) throws ChangeSetPersister.NotFoundException {
        return diaryService.updateDiary(id, updateDiaryRequest);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public boolean deleteDiary(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return diaryService.deleteDiary(id);
    }

}

