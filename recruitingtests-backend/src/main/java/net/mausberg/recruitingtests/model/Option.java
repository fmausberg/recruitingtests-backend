package net.mausberg.recruitingtests.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private String link;

    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "quiz_question_id")
    @JsonIgnore
    private QuizQuestion quizQuestion;
}