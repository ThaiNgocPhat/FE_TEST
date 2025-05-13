package com.ra.base_spring_boot.services;
import com.ra.base_spring_boot.dto.ResponseWrapper;
import com.ra.base_spring_boot.dto.req.UserAnswerDto;
import com.ra.base_spring_boot.dto.req.UserExamResultDto;
import com.ra.base_spring_boot.model.constants.SessionType;

import java.util.List;

public interface IUserAnswerService {
    ResponseWrapper<String> submitAnswers(List<UserAnswerDto> userAnswers);
    ResponseWrapper<UserExamResultDto> getUserExamResult(Long userId, Long examId, SessionType sessionType);
}
