package com.practice.pyeonhan_sesang.service;

import com.practice.pyeonhan_sesang.dto.request.DiaryInsertRequest;
import com.practice.pyeonhan_sesang.dto.request.DiaryUpdateRequest;
import com.practice.pyeonhan_sesang.dto.response.DiaryInsertResponse;
import com.practice.pyeonhan_sesang.dto.response.DiaryUpdateResponse;
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
    public DiaryInsertResponse insertDiary(DiaryInsertRequest diaryInsertRequest) {
        return DiaryInsertResponse.from(diaryRepository.save(diaryInsertRequest.toEntity()));
    }

    public List<DiaryInsertResponse> getAllDiaries() {
        List<Diary> diaries = diaryRepository.findAll();
        return DiaryInsertResponse.fromList(diaries);
    }

    public DiaryInsertResponse getDiary(Long id) {
        Optional<Diary> optionalDiary = diaryRepository.findById(id);
        if (optionalDiary.isPresent()) {
            Diary diary = optionalDiary.get();
            return DiaryInsertResponse.from(diary);
        } else {
            return null;
        }
    }

    @Transactional
    public DiaryUpdateResponse updateDiary(Long id, DiaryUpdateRequest diaryUpdateRequest) throws ChangeSetPersister.NotFoundException {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        diary.setId(diaryUpdateRequest.getId());
        diary.setAuthor(diaryUpdateRequest.getAuthor());
        diary.setTitle(diaryUpdateRequest.getTitle());
        diary.setContent(diaryUpdateRequest.getContent());
        diary.setWeather(diaryUpdateRequest.getWeather());
        diary.setCreated_at(diaryUpdateRequest.getCreated_at());
        diary.setUpdated_at(diaryUpdateRequest.getUpdated_at());

        return DiaryUpdateResponse.from(diaryRepository.save(diaryUpdateRequest.toEntity()));
    }

    @Transactional
    public void deleteDiary(Long id) {
        diaryRepository.findById(id);
    }

}
