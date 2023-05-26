package com.practice.pyeonhan_sesang.dto.request;

import com.practice.pyeonhan_sesang.entity.Diary;

import java.time.LocalDateTime;


public class CreateDiaryRequestTest {
    private String author;
    private String title;
    private String content;
    private String weather;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Diary toEntity() {
        return Diary.builder()
                .author(this.author)
                .title(this.title)
                .content(this.content)
                .weather(this.weather)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
    }

}