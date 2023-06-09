package com.practice.pyeonhan_sesang.repository;

import com.practice.pyeonhan_sesang.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByAuthor(String author);
}
