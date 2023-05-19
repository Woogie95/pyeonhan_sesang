package com.practice.pyeonhan_sesang.dto.response;

import com.practice.pyeonhan_sesang.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DiaryResponse {

    private Long id;

    private String author;

    private String title;

    private String content;

    private String weather;

    private LocalDateTime created_at;


    public static DiaryResponse from(Diary diary) {
        return new DiaryResponse(
                diary.getId(),
                diary.getAuthor(),
                diary.getTitle(),
                diary.getContent(),
                diary.getWeather(),
                diary.getCreated_at()
        );
    }

}
