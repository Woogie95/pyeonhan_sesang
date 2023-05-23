package com.practice.pyeonhan_sesang.dto.request;

import com.practice.pyeonhan_sesang.entity.Diary;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CreateDiaryRequest {

    @NotNull(message = "이름은 null 일 수 없습니다.")
    private String author;

    @NotNull(message = "제목은 null 일 수 없습니다.")
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
