package net.mausberg.recruitingtests.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private Long id;
    private LocalDateTime timestamp;
    private int complexity;
    private String category;
    private Long questionId;
    private String question;
    private Long appUserId;
    private Long givenAnswerId;

    private Long rightAnswer;
    private boolean isCorrect;
    private Long timeTaken;
    private double scoreBefore;
    private double scoreAfter; //users category score of the user after this answer
    private long countUserAnswers; // aount of answers given by the user before this answer
    private double difficultyBefore;
    private double difficultyAfter; // difficulty of the question after this answer
    private long countQuestionAnswers; // amount of answers given to this question before this answer
}