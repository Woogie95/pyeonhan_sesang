package com.practice.pyeonhan_sesang.dto.request;

import com.practice.pyeonhan_sesang.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DiaryInsertRequest {

    private String author;
    private String title;
    private String content;
    private String weather;
    private LocalDateTime created_at;

    public Diary toEntity() {
        return Diary.builder()
                .author(this.author)
                .title(this.title)
                .content(this.content)
                .weather(this.weather)
                .created_at(LocalDateTime.now())
                .build();
    }

}