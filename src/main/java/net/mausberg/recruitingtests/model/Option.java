package net.mausberg.recruitingtests.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    @OneToMany(mappedBy = "givenAnswer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Answer> answers; // Add this line to establish the relationship with Answer
}