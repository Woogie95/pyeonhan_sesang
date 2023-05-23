package com.practice.pyeonhan_sesang.dto.request;

import com.practice.pyeonhan_sesang.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UpdateDiaryRequest {

    private String title;
    private String content;
    private String weather;
    private String updated_at;

    public Diary toEntity() {
        return Diary.builder()
                .title(this.title)
                .content(this.content)
                .weather(this.weather)
                .updated_at(LocalDateTime.now())
                .build();
    }

}
