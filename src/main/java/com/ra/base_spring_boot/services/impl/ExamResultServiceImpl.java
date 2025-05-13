package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.dto.ResponseWrapper;
import com.ra.base_spring_boot.dto.req.ExamSessionScoreDto;
import com.ra.base_spring_boot.model.ExamResult;
import com.ra.base_spring_boot.repository.IExamResultRepository;
import com.ra.base_spring_boot.services.IExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements IExamResultService {
    private final IExamResultRepository examResultRepository;

    @Override
    public ResponseWrapper<List<ExamSessionScoreDto>> getResultsByUserAndExam(Long userId, Long examId) {
        List<ExamResult> results = examResultRepository.findByUserIdAndExamSession_Exam_Id(userId, examId);

        List<ExamSessionScoreDto> resultDTOs = results.stream()
                .map(result -> {
                    int score = (int) Math.round(((double) result.getCorrectAnswers() / result.getTotalQuestions()) * 100);
                    return new ExamSessionScoreDto(
                            result.getExamSession().getSessionType().toString(),
                            result.getCorrectAnswers(),
                            result.getTotalQuestions(),
                            score
                    );
                })
                .collect(Collectors.toList());

        ResponseWrapper<List<ExamSessionScoreDto>> response = new ResponseWrapper<>();
        response.setCode(200);
        response.setStatus(HttpStatus.OK);
        response.setData(resultDTOs);
        return response;
    }
}

