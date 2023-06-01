package com.practice.pyeonhan_sesang.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.pyeonhan_sesang.entity.Diary;
import com.practice.pyeonhan_sesang.entity.Gender;
import com.practice.pyeonhan_sesang.entity.User;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class CreateUserRequest {

    @NotNull(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    @NotNull(message = "패스워드는 필수 입력 항목입니다.")
    private String password;

    @NotNull(message = "휴대폰 번호는 필수 입력 항목입니다.")
    private String phone_number;

    @NotNull(message = "성별은 필수 입력 항목입니다.")
    private String gender;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .phone_number(this.phone_number)
                .gender(Gender.valueOf(this.gender))
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
    }

}
