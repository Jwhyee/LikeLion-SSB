package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    /*
     * Many : Answer
     * To
     * One : Question
     * 1번 답변 -> 1번 질문에 달림 O
     * 2번 답변 -> 1번 질문에 달림 O
     * 3번 답변 -> 1번 질문에 달림 O
     * 1번 답변 -> 2번 질문에 달림 X
     * 즉, 나라는 Answer는 1개의 Question에 묶여있다.
     */
    @ManyToOne
    private Question question;

    /*
     * Many : Answer
     * To
     * One : SiteUser
     * 1번 답변 -> user1 O
     * 2번 답변 -> user1 O
     * 3번 답변 -> user1 O
     * 1번 답변 -> user2 O
     * 즉, 나라는 답변은
     */
    @ManyToOne
    private SiteUser author;

    /*
     * Many : Answer
     * To
     * Many : SiteUser
     * 1번 답변 <-> user1 O
     * 1번 답변 <-> user2 O
     * 1번 답변 <-> user3 O
     * 2번 답변 <-> user1 O
     * 2번 답변 <-> user2 O
     * 즉, 하나의 유저는 다양한 답변을 추천할 수 있다.
     * DB 칼럼에 배열을 저장할 수 없다.
     *
     */
    @ManyToMany
    private Set<SiteUser> voter;
}
