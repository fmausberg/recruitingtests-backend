package net.mausberg.recruitingtests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.mausberg.recruitingtests.model.QuizQuestion;
import net.mausberg.recruitingtests.service.QuizQuestionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/quiz-questions")
public class QuizQuestionController {

    @Autowired
    private QuizQuestionService quizQuestionService;

    @GetMapping
    public List<QuizQuestion> getAllQuizQuestions() {
        return quizQuestionService.getAllQuizQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizQuestion> getQuizQuestionById(@PathVariable Long id) {
        Optional<QuizQuestion> quizQuestion = quizQuestionService.getQuizQuestionById(id);
        return quizQuestion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public QuizQuestion createQuizQuestion(@RequestBody QuizQuestion quizQuestion) {
        return quizQuestionService.saveQuizQuestion(quizQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizQuestion> updateQuizQuestion(@PathVariable Long id, @RequestBody QuizQuestion quizQuestion) {
        if (!quizQuestionService.getQuizQuestionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        quizQuestion.setId(id);
        return ResponseEntity.ok(quizQuestionService.saveQuizQuestion(quizQuestion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizQuestion(@PathVariable Long id) {
        if (!quizQuestionService.getQuizQuestionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        quizQuestionService.deleteQuizQuestion(id);
        return ResponseEntity.noContent().build();
    }
}