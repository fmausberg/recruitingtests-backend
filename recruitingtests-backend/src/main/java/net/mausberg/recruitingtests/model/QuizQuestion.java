package net.mausberg.recruitingtests.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int complexity;
    private String question;

    @ElementCollection
    private List<String> options = new ArrayList<>(5);
    private int correctAnswer;

}