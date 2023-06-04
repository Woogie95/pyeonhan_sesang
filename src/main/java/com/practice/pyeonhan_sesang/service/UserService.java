package com.practice.pyeonhan_sesang.service;

import com.practice.pyeonhan_sesang.dto.request.CreateUserRequest;
import com.practice.pyeonhan_sesang.dto.request.UpdateDiaryRequest;
import com.practice.pyeonhan_sesang.dto.request.UpdateUserRequest;
import com.practice.pyeonhan_sesang.dto.response.CreateUserResponse;
import com.practice.pyeonhan_sesang.dto.response.UpdateDiaryResponse;
import com.practice.pyeonhan_sesang.dto.response.UpdateUserResponse;
import com.practice.pyeonhan_sesang.dto.response.UserResponse;
import com.practice.pyeonhan_sesang.entity.Diary;
import com.practice.pyeonhan_sesang.entity.User;
import com.practice.pyeonhan_sesang.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    // 회원 등록
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        return CreateUserResponse.from(userRepository.save(createUserRequest.toEntity()));
    }

    // 전체 조회
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return UserResponse.fromList(users);
    }

    // 상세 조회
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserResponse.from(user);
        } else {
            return null;
        }
    }

    @Transactional
    public UpdateUserResponse updateUser(Long id, UpdateUserRequest updateUserRequest) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        Optional.ofNullable(updateUserRequest.getEmail())
                .ifPresent(user::setEmail);
        Optional.ofNullable(updateUserRequest.getPassword())
                .ifPresent(user::setPassword);
        Optional.ofNullable(updateUserRequest.getPhone_number())
                .ifPresent(user::setPhone_number);
        user.setUpdated_at(updateUserRequest.toEntity().getUpdated_at());
        return UpdateUserResponse.from(userRepository.save(user));
    }

    @Transactional
    public boolean deleteUser(Long id) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        userRepository.delete(user);
        return true;
    }

}

