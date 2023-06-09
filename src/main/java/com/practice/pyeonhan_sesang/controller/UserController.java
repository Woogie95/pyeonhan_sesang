package com.practice.pyeonhan_sesang.controller;

import com.practice.pyeonhan_sesang.dto.request.CreateUserRequest;
import com.practice.pyeonhan_sesang.dto.request.UpdateUserRequest;
import com.practice.pyeonhan_sesang.dto.response.*;
import com.practice.pyeonhan_sesang.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 등록
    @PostMapping()
    public CreateUserResponse createDiary(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    // 전체 조회
    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    // 상세 조회
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    // 회원글 조회
    @GetMapping("/{id}/diaries")
    public List<DiaryResponse> findAllUserDiaries(@PathVariable Long id) {
        return userService.findByUserIdAllDiaries(id);
    }

    // 수정
    @PutMapping("/{id}")
    public UpdateUserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) throws ChangeSetPersister.NotFoundException {
        return userService.updateUser(id, updateUserRequest);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return userService.deleteUser(id);
    }


}
