package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.dto.ResponseWrapper;
import com.ra.base_spring_boot.dto.req.ExamSessionScoreDto;

import java.util.List;

public interface IExamResultService {
    ResponseWrapper<List<ExamSessionScoreDto>> getResultsByUserAndExam(Long userId, Long examId);
}

