package net.mausberg.recruitingtests.service;

import net.mausberg.recruitingtests.dto.QuestionDTO;
import net.mausberg.recruitingtests.model.Answer;
import net.mausberg.recruitingtests.model.Question;
import net.mausberg.recruitingtests.repository.AnswerRepository;
import net.mausberg.recruitingtests.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Optional<QuestionDTO> getQuestionDTOById(Long id) {
        return questionRepository.findById(id).map(this::convertToDTO);
    }

    private QuestionDTO convertToDTO(Question question) {
        List<Long> timeTakens = question.getOptions().stream()
                .flatMap(option -> option.getAnswers().stream())
                .map(Answer::getTimeTaken)
                .sorted()
                .collect(Collectors.toList());

        DoubleSummaryStatistics stats = timeTakens.stream()
                .mapToDouble(Long::doubleValue)
                .summaryStatistics();

        double avgTimeTaken = stats.getAverage();
        double minTimeTaken = stats.getMin();
        double maxTimeTaken = stats.getMax();
        double medianTimeTaken = calculatePercentile(timeTakens, 50);
        double percentile10TimeTaken = calculatePercentile(timeTakens, 10);
        double percentile25TimeTaken = calculatePercentile(timeTakens, 25);
        double percentile75TimeTaken = calculatePercentile(timeTakens, 75);
        double percentile90TimeTaken = calculatePercentile(timeTakens, 90);

        long totalAnswers = timeTakens.size();

        double avgRight = totalAnswers > 0 ? question.getOptions().stream()
                .flatMap(option -> option.getAnswers().stream())
                .filter(answer -> answer.getGivenAnswer().isCorrect())
                .count() / (double) totalAnswers : 0.0;

        // Calculate difficulty based on the last answer's difficulty for this question
        Answer lastAnswer = answerRepository.findTopByQuestionOrderByTimestampDesc(question);
        double difficulty = lastAnswer != null ? lastAnswer.getDifficultyAfter() : 0.5; // Default to 0.5 if no answers

        return new QuestionDTO(
                question.getId(),
                question.getCategory().name(),
                question.getComplexity(),
                question.getQuestion(),
                question.getLink(),
                totalAnswers,
                avgTimeTaken,
                medianTimeTaken,
                minTimeTaken,
                maxTimeTaken,
                percentile10TimeTaken,
                percentile25TimeTaken,
                percentile75TimeTaken,
                percentile90TimeTaken,
                avgRight,
                difficulty
        );
    }

    private double calculatePercentile(List<Long> sortedValues, double percentile) {
        if (sortedValues.isEmpty()) {
            return 0.0;
        }
        int index = (int) Math.ceil(percentile / 100.0 * sortedValues.size());
        return sortedValues.get(index - 1);
    }
}