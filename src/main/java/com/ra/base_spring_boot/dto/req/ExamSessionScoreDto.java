package com.ra.base_spring_boot.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamSessionScoreDto {
    private String sessionType;
    private int correctAnswers;
    private int totalQuestions;
    private int score;
}
