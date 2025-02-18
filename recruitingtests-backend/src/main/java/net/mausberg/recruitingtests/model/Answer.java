package net.mausberg.recruitingtests.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import net.mausberg.authentication_framework_backend.model.AppUser;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime timestamp;
    
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "given_answer_id")
    private Option givenAnswer; 

    private long timeTaken;

    private double scoreBefore;
    private double scoreAfter; //users category score of the user after this answer
    private long countUserAnswers; // aount of answers given by the user before this answer
    private double difficultyBefore;
    private double difficultyAfter; // difficulty of the question after this answer
    private long countQuestionAnswers; // amount of answers given to this question before this answer
}
