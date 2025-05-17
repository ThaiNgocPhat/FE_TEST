package com.ra.base_spring_boot.repository;

import com.ra.base_spring_boot.model.UserAnswer;
import com.ra.base_spring_boot.model.constants.SessionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserAnswerRepository extends JpaRepository<UserAnswer, Long> {
    @Query("SELECT ua FROM UserAnswer ua " +
            "WHERE ua.userId = :userId " +
            "AND ua.question.id IN :questionIds " +
            "AND ua.question.examSession.sessionType = :sessionType")
    List<UserAnswer> findByUserAndQuestionIdsAndSessionType(
            @Param("userId") Long userId,
            @Param("questionIds") List<Long> questionIds,
            @Param("sessionType") SessionType sessionType
    );
}

