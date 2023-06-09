package com.practice.pyeonhan_sesang.dto.request;

import com.practice.pyeonhan_sesang.entity.Gender;
import com.practice.pyeonhan_sesang.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotNull(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotNull(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    @NotNull(message = "패스워드는 필수 입력 항목입니다.")
    private String password;

    @NotNull(message = "성별은 필수 선택 항목입니다.")
    private Gender gender;

    @NotNull(message = "휴대폰 번호는 필수 입력 항목입니다.")
    private String phone_number;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .gender(Gender.from(String.valueOf(this.gender)))
                .phone_number(this.phone_number)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
    }

}
