package net.mausberg.recruitingtests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mausberg.recruitingtests.dto.AnswerDTO;
import net.mausberg.recruitingtests.model.Answer;
import net.mausberg.recruitingtests.model.Option;
import net.mausberg.recruitingtests.model.Question;
import net.mausberg.recruitingtests.repository.AnswerRepository;
import net.mausberg.recruitingtests.repository.OptionRepository;
import net.mausberg.recruitingtests.repository.QuestionRepository;
import net.mausberg.authentication_framework_backend.model.AppUser;
import net.mausberg.authentication_framework_backend.repository.AppUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

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
        dto.setTimestamp(answer.getTimestamp());
        dto.setComplexity(answer.getQuestion().getComplexity());
        dto.setCategory(answer.getQuestion().getCategory().name());
        dto.setQuestionId(answer.getQuestion().getId());
        dto.setQuestion(answer.getQuestion().getQuestion());
        dto.setAppUserId(answer.getAppUser().getId());
        dto.setGivenAnswerId(answer.getGivenAnswer().getId());

        Option correctOption = answer.getQuestion().getOptions().stream()
                .filter(Option::isCorrect)
                .findFirst()
                .orElse(null);
        dto.setRightAnswer(correctOption != null ? correctOption.getId() : null);
        dto.setCorrect(answer.getGivenAnswer().isCorrect());
        dto.setTimeTaken(answer.getTimeTaken());
        dto.setScoreBefore(answer.getScoreBefore());
        dto.setScoreAfter(answer.getScoreAfter());
        dto.setCountUserAnswers(answer.getCountUserAnswers());
        dto.setDifficultyBefore(answer.getDifficultyBefore());
        dto.setDifficultyAfter(answer.getDifficultyAfter());
        dto.setCountQuestionAnswers(answer.getCountQuestionAnswers());

        return dto;
    }

    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElse(null);
    }

    public Answer saveAnswer(AnswerDTO answerDTO) {
        AppUser appUser = appUserRepository.findById(answerDTO.getAppUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Question question = questionRepository.findById(answerDTO.getQuestionId()).orElseThrow(() -> new RuntimeException("Question not found"));
        Option givenAnswer = optionRepository.findById(answerDTO.getGivenAnswerId()).orElseThrow(() -> new RuntimeException("Option not found"));

        Answer answer = new Answer();
        answer.setId(answerDTO.getId());
        answer.setTimestamp(java.time.LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAppUser(appUser);
        answer.setGivenAnswer(givenAnswer);
        answer.setTimeTaken(answerDTO.getTimeTaken());
        answer.setScoreBefore(answerDTO.getScoreBefore());
        answer.setScoreAfter(answerDTO.getScoreAfter());
        answer.setCountUserAnswers(answerDTO.getCountUserAnswers());
        answer.setDifficultyBefore(answerDTO.getDifficultyBefore());
        answer.setDifficultyAfter(answerDTO.getDifficultyAfter());
        answer.setCountQuestionAnswers(answerDTO.getCountQuestionAnswers());
         
        Answer previousUserAnswer = answerRepository.findTopByAppUserAndTimestampBeforeAndQuestionCategoryOrderByTimestampDesc(answer.getAppUser(), answer.getTimestamp(), answer.getQuestion().getCategory());
        Answer previousQuestionAnswer = answerRepository.findTopByQuestionAndTimestampBeforeAndQuestionCategoryOrderByTimestampDesc(answer.getQuestion(), answer.getTimestamp(), answer.getQuestion().getCategory());
        double correctness = answer.getGivenAnswer().isCorrect() ? 1.0 : 0.0;

        // Check if this is the first answer by the user
        if (previousUserAnswer == null) {
            answer.setScoreBefore(0.5);
            answer.setCountUserAnswers(1);
        } else {
            answer.setScoreBefore(previousUserAnswer.getScoreAfter());
            answer.setCountUserAnswers(previousUserAnswer.getCountUserAnswers() + 1);
        }

        // Check if this is the first answer to this question
        if (previousQuestionAnswer == null) {
            answer.setDifficultyBefore(0.5);
            answer.setCountQuestionAnswers(1);
        } else {
            answer.setDifficultyBefore(previousQuestionAnswer.getDifficultyAfter());
            answer.setCountQuestionAnswers(previousQuestionAnswer.getCountQuestionAnswers() + 1);
        }

        // calculation of score and difficulty after this answer
        answer.setScoreAfter((answer.getCountUserAnswers() * answer.getScoreBefore() + correctness * answer.getDifficultyBefore()) / (answer.getCountUserAnswers() + answer.getDifficultyBefore()));
        answer.setDifficultyAfter((answer.getCountQuestionAnswers() * answer.getDifficultyBefore() + (1 - correctness) * answer.getScoreBefore()) / (answer.getCountQuestionAnswers() + answer.getScoreBefore()));

        return answerRepository.save(answer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}