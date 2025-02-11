package net.mausberg.recruitingtests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.mausberg.recruitingtests.model.Answer;
import net.mausberg.recruitingtests.repository.AnswerRepository;
import net.mausberg.authentication_framework_backend.model.AppUser;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public List<Answer> getAnswersByAppUser(AppUser appUser) {
        return answerRepository.findByAppUser(appUser);
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    public Answer saveAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}