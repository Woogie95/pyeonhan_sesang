package com.practice.pyeonhan_sesang.dto.response;

import com.practice.pyeonhan_sesang.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateDiaryResponse {

    private Long id;
    private String author;
    private String title;
    private String content;
    private String weather;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static UpdateDiaryResponse from(Diary diary) {
        return new UpdateDiaryResponse(
                diary.getId(),
                diary.getAuthor(),
                diary.getTitle(),
                diary.getContent(),
                diary.getWeather(),
                diary.getCreated_at(),
                diary.getUpdated_at()
        );
    }

}
