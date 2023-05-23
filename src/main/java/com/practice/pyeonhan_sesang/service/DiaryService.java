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

    @Transactional
    public List<DiaryResponse> getAllDiaries() {
        List<Diary> diaries = diaryRepository.findAll();
        return DiaryResponse.fromList(diaries);
    }

    @Transactional
    public DiaryResponse getDetailDiary(Long id) {
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
        if (updateDiaryRequest.getTitle() != null) {
            diary.setTitle(updateDiaryRequest.getTitle());
        }
        if (updateDiaryRequest.getContent() != null) {
            diary.setContent(updateDiaryRequest.getContent());
        }
        if (updateDiaryRequest.getWeather() != null) {
            diary.setWeather(updateDiaryRequest.getWeather());
        }

        diary.setUpdated_at(updateDiaryRequest.toEntity().getUpdated_at());
        return UpdateDiaryResponse.from(diaryRepository.save(diary));
    }


    @Transactional
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

}
