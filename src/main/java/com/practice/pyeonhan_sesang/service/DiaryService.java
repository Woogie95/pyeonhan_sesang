package com.practice.pyeonhan_sesang.service;

import com.practice.pyeonhan_sesang.dto.request.DiaryRequest;
import com.practice.pyeonhan_sesang.dto.response.DiaryResponse;
import com.practice.pyeonhan_sesang.entity.Diary;
import com.practice.pyeonhan_sesang.repository.DiaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiaryService {

    private DiaryRepository diaryRepository;

    @Transactional
    public DiaryResponse insertDiary(DiaryRequest diaryRequest) {
        return DiaryResponse.from(diaryRepository.save(diaryRequest.toEntity()));
    }

    public List<DiaryResponse> getAllDiaries() {
        List<Diary> diaries = diaryRepository.findAll();
        return DiaryResponse.fromList(diaries);
    }

    public DiaryResponse getDiary(Long id) {
        Optional<Diary> optionalDiary = diaryRepository.findById(id);
        if (optionalDiary.isPresent()) {
            Diary diary = optionalDiary.get();
            return DiaryResponse.from(diary);
        } else {
            return null;
        }
    }


}
