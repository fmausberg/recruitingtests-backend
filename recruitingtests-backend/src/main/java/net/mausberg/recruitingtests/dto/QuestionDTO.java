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
    private long totalAnswers; // total amount of answers given to this question
    private double avgTimeTaken; // average time taken value of answers referencing to this question
    private double medianTimeTaken;
    private double minTimeTaken;
    private double maxTimeTaken;
    private double percentile10TimeTaken;
    private double percentile25TimeTaken;
    private double percentile75TimeTaken;
    private double percentile90TimeTaken;
    private double avgRight; // average of right answers given to this question
    private double difficulty;
}