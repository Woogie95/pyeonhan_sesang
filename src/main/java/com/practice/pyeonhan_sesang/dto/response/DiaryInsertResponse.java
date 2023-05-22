package com.practice.pyeonhan_sesang.dto.response;

import com.practice.pyeonhan_sesang.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DiaryInsertResponse {

    private Long id;
    private String author;
    private String title;
    private String content;
    private String weather;
    private LocalDateTime created_at;

    public static DiaryInsertResponse from(Diary diary) {
        return new DiaryInsertResponse(
                diary.getId(),
                diary.getAuthor(),
                diary.getTitle(),
                diary.getContent(),
                diary.getWeather(),
                diary.getCreated_at()
        );
    }

    public static List<DiaryInsertResponse> fromList(List<Diary> diaries) {
        List<DiaryInsertResponse> responses = new ArrayList<>();
        for (Diary diary : diaries) {
            responses.add(from(diary));
        }
        return responses;
    }

}
