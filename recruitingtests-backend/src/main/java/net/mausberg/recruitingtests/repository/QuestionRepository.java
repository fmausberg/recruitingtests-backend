package net.mausberg.recruitingtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.mausberg.recruitingtests.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}