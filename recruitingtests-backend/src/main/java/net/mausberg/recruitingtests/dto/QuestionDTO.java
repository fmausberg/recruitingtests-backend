package net.mausberg.recruitingtests.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String category;
    private int complexity;
    private String question;
    private String link;
    private double avgTimeTaken; // average time taken value of answers referencing to this question
    private double avgRight; // average of right answers given to this question
}