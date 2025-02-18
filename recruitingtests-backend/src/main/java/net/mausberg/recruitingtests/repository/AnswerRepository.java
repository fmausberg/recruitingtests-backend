package net.mausberg.recruitingtests.repository;

import net.mausberg.recruitingtests.model.Answer;
import net.mausberg.recruitingtests.model.Question;
import net.mausberg.authentication_framework_backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Repository interface for managing Answer entities.
 * Extends JpaRepository to provide basic CRUD operations and custom query methods.
 * 
 * <p>This repository provides methods to:
 * <ul>
 *   <li>Retrieve a list of answers associated with a specific AppUser</li>
 *   <li>Retrieve a list of answers associated with a specific user ID</li>
 *   <li>Retrieve the most recent Answer for a given AppUser created before a specified timestamp</li>
 *   <li>Retrieve the most recent Answer for a given Question before a specified timestamp</li>
 * </ul>
 * </p>
 * 
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see net.mausberg.recruitingtests.model.Answer
 * @see net.mausberg.recruitingtests.model.AppUser
 * @see net.mausberg.recruitingtests.model.Question
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
    
    /**
     * Retrieves a list of answers associated with a specific AppUser.
     *
     * @param appUser the user whose answers are to be retrieved
     * @return a list of answers associated with the specified AppUser
     */
    List<Answer> findByAppUser(AppUser appUser);

    /**
     * Retrieves a list of answers associated with a specific user ID.
     *
     * @param userId the ID of the user whose answers are to be retrieved
     * @return a list of answers associated with the specified user ID
     */
    List<Answer> findByAppUserId(Long userId);
    
    /**
     * Retrieves the most recent Answer for a given AppUser that was created before the specified timestamp.
     *
     * @param appUser the user for whom the answer is being retrieved
     * @param timestamp the cutoff timestamp; only answers created before this timestamp will be considered
     * @return the most recent Answer for the given AppUser created before the specified timestamp, or null if no such answer exists
     */
    Answer findTopByAppUserAndTimestampBeforeOrderByTimestampDesc(AppUser appUser, LocalDateTime timestamp);

    
    /**
     * Finds the most recent Answer for a given Question before a specified timestamp.
     *
     * @param question the Question for which the Answer is being searched
     * @param timestamp the cutoff timestamp; only Answers before this timestamp are considered
     * @return the most recent Answer before the specified timestamp for the given Question
     */
    Answer findTopByQuestionAndTimestampBeforeOrderByTimestampDesc(Question question, LocalDateTime timestamp);
}