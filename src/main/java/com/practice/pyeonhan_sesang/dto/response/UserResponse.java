package com.practice.pyeonhan_sesang.dto.response;

import com.practice.pyeonhan_sesang.entity.Diary;
import com.practice.pyeonhan_sesang.entity.Gender;
import com.practice.pyeonhan_sesang.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long user_id;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private String phone_number;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static UserResponse from(User user) {
        return new UserResponse(
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

    public static List<UserResponse> fromList(List<User> users) {
        List<UserResponse> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(from(user));
        }
        return responses;
    }

}

