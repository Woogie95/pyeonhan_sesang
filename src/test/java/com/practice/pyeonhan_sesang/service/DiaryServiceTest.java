//package com.practice.pyeonhan_sesang.service;
//
//import com.practice.pyeonhan_sesang.dto.request.CreateDiaryRequest;
//import com.practice.pyeonhan_sesang.dto.request.UpdateDiaryRequest;
//import com.practice.pyeonhan_sesang.dto.response.CreateDiaryResponse;
//import com.practice.pyeonhan_sesang.dto.response.DiaryResponse;
//import com.practice.pyeonhan_sesang.dto.response.UpdateDiaryResponse;
//import com.practice.pyeonhan_sesang.entity.Diary;
//import com.practice.pyeonhan_sesang.repository.DiaryRepository;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.crossstore.ChangeSetPersister;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.Random;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.doNothing;
//
//@ExtendWith(MockitoExtension.class)
//class DiaryServiceTest {
//
//    @Mock
//    private DiaryRepository diaryRepository;
//
//    @InjectMocks
//    private DiaryService diaryService;
//
//    @Test
//    @DisplayName("글 등록")
//    void createDiary() {
//        // given
//        Long id = new Random().nextLong(Long.MAX_VALUE) + 1;
//        Diary diary = Diary.builder()
//                .id(id)
//                .author("최성욱")
//                .title("제목")
//                .content("내용")
//                .weather("날씨")
//                .created_at(LocalDateTime.of(2023, 5, 1, 10, 30))
//                .updated_at(LocalDateTime.of(2023, 5, 1, 10, 30))
//                .build();
//        given(diaryRepository.save(any())).willReturn(diary);
//
//        CreateDiaryResponse createDiaryResponse = new CreateDiaryResponse(
//                id, "최성욱", "제목", "내용", "날씨",
//                LocalDateTime.of(2023, 5, 1, 10, 30),
//                LocalDateTime.of(2023, 5, 1, 10, 30));
//
//        // when
//        CreateDiaryResponse expectedDiaryResponse = diaryService.createDiary(new CreateDiaryRequest(
//                "최성욱", "제목", "내용", "날씨",
//                LocalDateTime.now(), LocalDateTime.now()));
//
//        // then
//        Assertions.assertThat(createDiaryResponse).usingRecursiveComparison().isEqualTo(expectedDiaryResponse);
//    }
//
//    @Test
//    @DisplayName("전체 조회")
//    void findAll() {
//        // given
//        List<Diary> diaries = List.of(
//                new Diary(1L, "최성욱", "제목1", "내용1", "날씨1",
//                        LocalDateTime.of(2023, 5, 31, 4, 0),
//                        LocalDateTime.of(2023, 5, 31, 4, 0)),
//                new Diary(2L, "홍길동", "제목2", "내용2", "날씨2",
//                        LocalDateTime.of(2023, 5, 31, 4, 0),
//                        LocalDateTime.of(2023, 5, 31, 4, 0)));
//        given(diaryRepository.findAll()).willReturn(diaries);
//
//        List<DiaryResponse> expectedDiariesResponse = List.of(
//                new DiaryResponse(1L, "최성욱", "제목1", "내용1", "날씨1",
//                        LocalDateTime.of(2023, 5, 31, 4, 0),
//                        LocalDateTime.of(2023, 5, 31, 4, 0)),
//                new DiaryResponse(2L, "홍길동", "제목2", "내용2", "날씨2",
//                        LocalDateTime.of(2023, 5, 31, 4, 0),
//                        LocalDateTime.of(2023, 5, 31, 4, 0)));
//
//        // when
//        List<DiaryResponse> findAllDiaries = diaryService.findAll();
//
//        // then
//        assertThat(findAllDiaries).hasSize(expectedDiariesResponse.size())
//                .usingRecursiveComparison()
//                .isEqualTo(expectedDiariesResponse);
//    }
//
//    @Test
//    @DisplayName("상세 조회")
//    void findById() {
//        // given
//        Long id = new Random().nextLong(Long.MAX_VALUE) + 1;
//        Diary diary = Diary.builder()
//                .id(id)
//                .author("최성욱")
//                .title("제목")
//                .content("내용")
//                .weather("날씨")
//                .created_at(LocalDateTime.of(2023, 5, 31, 4, 0))
//                .updated_at(LocalDateTime.of(2023, 5, 31, 4, 0))
//                .build();
//        given(diaryRepository.findById(any())).willReturn(Optional.ofNullable(diary));
//        DiaryResponse diaryResponse = diaryService.findById(id);
//
//        // when
//        DiaryResponse expectedDiaryResponse = new DiaryResponse(
//                id, "최성욱", "제목", "내용", "날씨",
//                LocalDateTime.of(2023, 5, 31, 4, 0),
//                LocalDateTime.of(2023, 5, 31, 4, 0));
//
//        // then
//        assertThat(diaryResponse).usingRecursiveComparison().isEqualTo(expectedDiaryResponse);
//    }
//
//    @Test
//    @DisplayName("글 수정")
//    void updateDiary() throws ChangeSetPersister.NotFoundException {
//        // given
//        Long id = new Random().nextLong(Long.MAX_VALUE) + 1;
//        Diary diary = Diary.builder()
//                .id(id)
//                .author("최성욱")
//                .title("제목")
//                .content("내용")
//                .weather("날씨")
//                .created_at(LocalDateTime.of(2023, 5, 31, 10, 30))
//                .updated_at(LocalDateTime.of(2023, 5, 31, 10, 30))
//                .build();
//        given(diaryRepository.findById(any())).willReturn(Optional.ofNullable(diary));
//
//        Diary modifyDiary = Diary.builder()
//                .id(id)
//                .author("최성욱")
//                .title("새로운 제목")
//                .content("새로운 내용")
//                .weather("새로운 날씨")
//                .created_at(LocalDateTime.of(2023, 5, 31, 10, 30))
//                .updated_at(LocalDateTime.of(2023, 5, 31, 7, 0))
//                .build();
//        given(diaryRepository.save(any())).willReturn(modifyDiary);
//
//        UpdateDiaryRequest updateDiaryRequest = new UpdateDiaryRequest(
//                "새로운 제목", "새로운 내용", "새로운 날씨",
//                LocalDateTime.of(2023, 5, 31, 7, 0));
//
//        UpdateDiaryResponse actualDiaryResponse = new UpdateDiaryResponse(
//                id, "최성욱", "새로운 제목", "새로운 내용", "새로운 날씨",
//                LocalDateTime.of(2023, 5, 31, 10, 30),
//                LocalDateTime.of(2023, 5, 31, 7, 0));
//
//        // when
//        UpdateDiaryResponse expectedUpdateDiaryResponse = diaryService.updateDiary(id, updateDiaryRequest);
//
//        // then
//        assertThat(actualDiaryResponse).usingRecursiveComparison().isEqualTo(expectedUpdateDiaryResponse);
//    }
//
//    @Test
//    @DisplayName("글 삭제")
//    void deleteDiary() throws ChangeSetPersister.NotFoundException {
//        // given
//        Long id = new Random().nextLong(Long.MAX_VALUE) + 1;
//        given(diaryRepository.findById(anyLong())).willReturn(Optional.of(Diary.builder().build()));
//        doNothing().when(diaryRepository).delete(any());
//
//        // when
//        boolean isDelete = diaryService.deleteDiary(id);
//
//        // then
//        assertThat(isDelete).isTrue();
//    }
//}