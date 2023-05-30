package com.practice.pyeonhan_sesang.dto.request;

import com.practice.pyeonhan_sesang.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDiaryRequest {

    private String title;
    private String content;
    private String weather;
    private LocalDateTime updated_at;

    public Diary toEntity() {
        return Diary.builder()
                .title(this.title)
                .content(this.content)
                .weather(this.weather)
                .updated_at(LocalDateTime.now())
                .build();
    }

}
