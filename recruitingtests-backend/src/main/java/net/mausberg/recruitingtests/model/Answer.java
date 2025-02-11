package net.mausberg.recruitingtests.model;

import java.util.Date;

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
    private Date timestamp;
    
    @ManyToOne
    @JoinColumn(name = "quiz_question_id")
    private QuizQuestion quizQuestion;
    
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    private int answer;
    private long timeTaken;

}
