package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.DataNotFoundException;
import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.question.QuestionForm;
import com.ll.exam.sbb.question.QuestionService;
import com.ll.exam.sbb.user.SiteUser;
import com.ll.exam.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PostMapping("/create/{id}")
    @PreAuthorize("isAuthenticated()")
    public String detail(Principal principal, Model model, @PathVariable long id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
        Question question = this.questionService.getQuestion(id);

        if ( bindingResult.hasErrors() ) {
            model.addAttribute("question", question);
            return "question_detail";
        }

        SiteUser siteUser = userService.getUser(principal.getName());

        // 답변 등록 시작
        answerService.create(question, answerForm.getContent(), siteUser);
        // 답변 등록 끝

        return "redirect:/question/detail/%d".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal) {
        Answer answer = this.answerService.getAnswer(id);

        if (answer == null) {
            throw new DataNotFoundException("%d번 질문은 존재하지 않습니다.");
        }

        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        answerForm.setContent(answer.getContent());

        return "answer_form";
    }

    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "redirect:/question/detail/{id}";
        }
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        System.out.println("answerForm.getContent() = " + answerForm.getContent());
        this.answerService.modify(answer, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @GetMapping("/delete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.answerService.delete(answer);
        return "redirect:/question/detail/{id}";
    }
}
