package net.mausberg.recruitingtests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import net.mausberg.recruitingtests.dto.AnswerDTO;
import net.mausberg.recruitingtests.model.Answer;
import net.mausberg.recruitingtests.service.AnswerService;
import net.mausberg.authentication_framework_backend.model.AppUser;
import net.mausberg.authentication_framework_backend.service.AppUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v0/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AppUserService appUserService;

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
    public ResponseEntity<List<AnswerDTO>> getAnswersByAppUser(@PathVariable Long userId) {
        List<AnswerDTO> answers = answerService.getAnswersByAppUserId(userId);
        return ResponseEntity.ok(answers);
    }

    @PostMapping
    public Answer createAnswer(@RequestBody AnswerDTO answerDTO) {
        // Get the current authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        AppUser appUser = appUserService.getAppUserByUsername(username);

        // Set the appUser in the answer
        answerDTO.setAppUserId(appUser.getId());

        return answerService.saveAnswer(answerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody AnswerDTO answerDTO) {
        if (answerService.getAnswerById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        answerDTO.setId(id);
        return ResponseEntity.ok(answerService.saveAnswer(answerDTO));
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