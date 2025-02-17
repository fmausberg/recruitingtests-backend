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
    private Long quizQuestionId;
    private String quizQuestion;
    private Long appUserId;
    private Long givenAnswer;
    private Long rightAnswer;
    private boolean isCorrect;
    private Long timeTaken;
}