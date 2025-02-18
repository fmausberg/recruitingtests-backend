package net.mausberg.recruitingtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.mausberg.recruitingtests.model.Question;

/**
 * Repository interface for managing {@link Question} entities.
 * 
 * <p>This interface extends {@link JpaRepository}, providing several methods
 * for interacting with the database, such as saving, deleting, and finding
 * {@link Question} entities.</p>
 * 
 * <p>Spring Data JPA will automatically generate the implementation of this
 * interface at runtime.</p>
 * 
 * @see JpaRepository
 * @see Question
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}