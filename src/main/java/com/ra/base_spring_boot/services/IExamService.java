package com.ra.base_spring_boot.services;

import com.ra.base_spring_boot.dto.MessageResponse;
import com.ra.base_spring_boot.dto.PaginatedResponse;
import com.ra.base_spring_boot.dto.ResponseWrapper;
import com.ra.base_spring_boot.dto.req.ExamDto;
import com.ra.base_spring_boot.model.Exam;

public interface IExamService {
    ResponseWrapper<Exam> createExam(ExamDto req);
    PaginatedResponse<Exam> getExams(int page, int limit);
    ResponseWrapper<Exam> updateExam(Long id, ExamDto req);
    MessageResponse deleteExam(Long id);
    Exam getExamById(Long id);
}
