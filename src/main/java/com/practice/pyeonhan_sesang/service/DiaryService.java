package com.practice.pyeonhan_sesang.service;

import com.practice.pyeonhan_sesang.dto.request.CreateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.request.UpdateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.response.DiaryResponse;
import com.practice.pyeonhan_sesang.dto.response.CreateDiaryResponse;
import com.practice.pyeonhan_sesang.dto.response.UpdateDiaryResponse;
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
    public CreateDiaryResponse createDiary(CreateDiaryRequest createDiaryRequest) {
        return CreateDiaryResponse.from(diaryRepository.save(createDiaryRequest.toEntity()));
    }

    public List<DiaryResponse> findAll() {
        List<Diary> diaries = diaryRepository.findAll();
        return DiaryResponse.fromList(diaries);
    }

    public DiaryResponse findById(Long id) {
        Optional<Diary> optionalDiary = diaryRepository.findById(id);
        if (optionalDiary.isPresent()) {
            Diary diary = optionalDiary.get();
            return DiaryResponse.from(diary);
        } else {
            return null;
        }
    }

    @Transactional
    public UpdateDiaryResponse updateDiary(Long id, UpdateDiaryRequest updateDiaryRequest) throws ChangeSetPersister.NotFoundException {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        Optional.ofNullable(updateDiaryRequest.getTitle())
                .ifPresent(diary::setTitle);
        Optional.ofNullable(updateDiaryRequest.getContent())
                .ifPresent(diary::setContent);
        Optional.ofNullable(updateDiaryRequest.getWeather())
                .ifPresent(diary::setWeather);
        diary.setUpdated_at(updateDiaryRequest.toEntity().getUpdated_at());
        return UpdateDiaryResponse.from(diaryRepository.save(diary));
    }

    @Transactional
    public boolean deleteDiary(Long id) throws ChangeSetPersister.NotFoundException {
        Diary diary = diaryRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        diaryRepository.delete(diary);
        return true;
    }

}
