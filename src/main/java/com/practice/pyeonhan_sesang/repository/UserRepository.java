package com.practice.pyeonhan_sesang.repository;

import com.practice.pyeonhan_sesang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
