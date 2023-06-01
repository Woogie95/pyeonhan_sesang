package com.practice.pyeonhan_sesang.controller;

import com.practice.pyeonhan_sesang.dto.request.CreateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.response.CreateDiaryResponse;
import com.practice.pyeonhan_sesang.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 가입

}
