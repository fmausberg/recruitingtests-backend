package net.mausberg.recruitingtests.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.mausberg.recruitingtests.model.Answer;
import net.mausberg.authentication_framework_backend.model.AppUser;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByAppUser(AppUser appUser);
}