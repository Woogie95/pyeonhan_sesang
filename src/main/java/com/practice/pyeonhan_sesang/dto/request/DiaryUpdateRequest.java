package com.practice.pyeonhan_sesang.dto.request;

import com.practice.pyeonhan_sesang.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class DiaryUpdateRequest {

    private Long id;
    private String author;
    private String title;
    private String content;
    private String weather;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Diary toEntity() {
        return Diary.builder()
                .id(this.id)
                .author(this.author)
                .title(this.title)
                .content(this.content)
                .weather(this.weather)
                .created_at(created_at)
                .updated_at(LocalDateTime.now())
                .build();
    }

}
