package net.mausberg.recruitingtests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mausberg.recruitingtests.dto.AnswerDTO;
import net.mausberg.recruitingtests.model.Answer;
import net.mausberg.recruitingtests.repository.AnswerRepository;
import net.mausberg.authentication_framework_backend.model.AppUser;
import net.mausberg.recruitingtests.model.Option;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    public List<AnswerDTO> getAnswersByAppUser(AppUser appUser) {
        List<Answer> answers = answerRepository.findByAppUser(appUser);
        return answers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<AnswerDTO> getAnswersByAppUserId(Long userId) {
        List<Answer> answers = answerRepository.findByAppUserId(userId);
        return answers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AnswerDTO convertToDTO(Answer answer) {
        AnswerDTO dto = new AnswerDTO();
        dto.setId(answer.getId());
        dto.setTimestamp(answer.getTimestamp().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        dto.setComplexity(answer.getQuestion().getComplexity());
        dto.setCategory(answer.getQuestion().getCategory().name());
        dto.setQuestionId(answer.getQuestion().getId());
        dto.setQuestion(answer.getQuestion().getQuestion());
        dto.setAppUserId(answer.getAppUser().getId());
        dto.setGivenAnswer(answer.getGivenAnswer().getId());

        Option correctOption = answer.getQuestion().getOptions().stream()
                .filter(Option::isCorrect)
                .findFirst()
                .orElse(null);
        dto.setRightAnswer(correctOption != null ? correctOption.getId() : null);
        dto.setCorrect(answer.getGivenAnswer().isCorrect());
        dto.setTimeTaken(answer.getTimeTaken());

        return dto;
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