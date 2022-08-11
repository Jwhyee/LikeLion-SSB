package com.ll.exam.sbb;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    void test_list() {
        truncate_Test();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void truncate_Test() {
        // given
        questionRepository.truncateQuestion();

        // when
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        Question q = questionRepository.save(q1);

        // then
        assertEquals(q.getId(), 1);
        questionRepository.truncateQuestion();
    }

    @Test
    void save_Test() {
        // given
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());

        // when
        Question q = questionRepository.save(q1);

        // then
        assertEquals(q.getId(), 1);
    }

    @Test
    void delete_Test() {
        // given
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        Question q = questionRepository.save(q1);

        // when
        questionRepository.delete(q);

        // then
        assertEquals(questionRepository.findAll().size(), 0);
    }

    @Test
    void update_Test() {
        // given
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        Question q = questionRepository.save(q1);

        // when
        q.setSubject("sbb가 무엇?");
        q.setContent("sbb에 대해서 알고 싶");

        // then
        assertEquals(q.getSubject(), "sbb가 무엇?");
    }

    @Test
    void findBySubject_Test() {
        // given
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("sbb가 무엇");
        q2.setContent("sbb에 대해서 알고 싶");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        // when
        Question fq1 = questionRepository.findBySubject("sbb가 무엇인가요?");
        Question fq2 = questionRepository.findBySubject("sbb가 무엇");

        // then
        assertEquals(fq1.getId(), 1);
        assertEquals(fq2.getId(), 2);
    }

    @Test
    void findBySubjectAndContent_Test() {
        // given
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("sbb가 무엇");
        q2.setContent("sbb에 대해서 알고 싶");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        // when
        Question fq1 = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
        Question fq2 = questionRepository.findBySubjectAndContent("sbb가 무엇", "sbb에 대해서 알고 싶");

        // then
        assertEquals(fq1.getId(), 1);
        assertEquals(fq2.getId(), 2);
    }

    @Test
    void findBySubjectLike_Test() {
        // given
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("sbb가 무엇");
        q2.setContent("sbb에 대해서 알고 싶");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        // when
        List<Question> fq = questionRepository.findBySubjectLike("%무엇%");

        // then
        assertEquals(fq.size(), 2);
    }

}