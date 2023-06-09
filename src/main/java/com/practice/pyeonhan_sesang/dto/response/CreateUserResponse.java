package com.practice.pyeonhan_sesang.dto.response;

import com.practice.pyeonhan_sesang.entity.Diary;
import com.practice.pyeonhan_sesang.entity.Gender;
import com.practice.pyeonhan_sesang.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateUserResponse {

    private Long user_id;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private String phone_number;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static CreateUserResponse from(User user) {
        return new CreateUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getGender(),
                user.getPhone_number(),
                user.getCreated_at(),
                user.getUpdated_at()
        );
    }

}
