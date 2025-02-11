package net.mausberg.recruitingtests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.mausberg.recruitingtests.model.Answer;
import net.mausberg.recruitingtests.service.AnswerService;
import net.mausberg.authentication_framework_backend.model.AppUser;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        Answer answer = answerService.getAnswerById(id);
        return answer != null ? ResponseEntity.ok(answer) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Answer>> getAnswersByAppUser(@PathVariable Long userId) {
        AppUser appUser = new AppUser();
        appUser.setId(userId);
        List<Answer> answers = answerService.getAnswersByAppUser(appUser);
        return ResponseEntity.ok(answers);
    }

    @PostMapping
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer answer) {
        if (answerService.getAnswerById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        answer.setId(id);
        return ResponseEntity.ok(answerService.saveAnswer(answer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        if (answerService.getAnswerById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}