package com.practice.pyeonhan_sesang.dto.response;

import com.practice.pyeonhan_sesang.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiaryResponse {

    private Long id;
    private String author;
    private String title;
    private String content;
    private String weather;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static DiaryResponse from(Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getAuthor(),
                diary.getTitle(),
                diary.getContent(),
                diary.getWeather(),
                diary.getCreated_at(),
                diary.getUpdated_at()
        );
    }

    public static List<DiaryResponse> fromList(List<Diary> diaries) {
        List<DiaryResponse> responses = new ArrayList<>();
        for (Diary diary : diaries) {
            responses.add(from(diary));
        }
        return responses;
    }

}
