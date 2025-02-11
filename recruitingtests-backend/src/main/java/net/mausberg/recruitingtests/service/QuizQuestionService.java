package net.mausberg.recruitingtests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.mausberg.recruitingtests.model.QuizQuestion;
import net.mausberg.recruitingtests.repository.QuizQuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizQuestionService {

    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public List<QuizQuestion> getAllQuizQuestions() {
        return quizQuestionRepository.findAll();
    }

    public Optional<QuizQuestion> getQuizQuestionById(Long id) {
        return quizQuestionRepository.findById(id);
    }

    public QuizQuestion saveQuizQuestion(QuizQuestion quizQuestion) {
        return quizQuestionRepository.save(quizQuestion);
    }

    public void deleteQuizQuestion(Long id) {
        quizQuestionRepository.deleteById(id);
    }
}