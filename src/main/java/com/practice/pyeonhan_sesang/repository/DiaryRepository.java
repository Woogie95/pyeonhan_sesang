package com.practice.pyeonhan_sesang.repository;

import com.practice.pyeonhan_sesang.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
