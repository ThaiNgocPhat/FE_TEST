package com.ra.base_spring_boot.services.impl;

import com.ra.base_spring_boot.dto.MessageResponse;
import com.ra.base_spring_boot.dto.PaginatedResponse;
import com.ra.base_spring_boot.dto.ResponseWrapper;
import com.ra.base_spring_boot.dto.req.ExamDto;
import com.ra.base_spring_boot.exception.HttpConflict;
import com.ra.base_spring_boot.exception.HttpNotFound;
import com.ra.base_spring_boot.model.Exam;
import com.ra.base_spring_boot.repository.IExamRepository;
import com.ra.base_spring_boot.services.IExamService;
import com.ra.base_spring_boot.services.IExamSessionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements IExamService {
    private final IExamRepository examRepository;
    private final ModelMapper modelMapper;
    private final IExamSessionService examSessionService;

    @Override
    public ResponseWrapper<Exam> updateExam(Long id, ExamDto req) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new HttpNotFound("Exam not found with id: " + id));
        List<Exam> existingExams = examRepository.findByExamName(req.getExamName());
        for (Exam existing : existingExams) {
            if (!existing.getId().equals(id)) {
                throw new HttpConflict("Exam name already exists");
            }
        }
        new ModelMapper().map(req, exam);
        Exam updatedExam = examRepository.save(exam);
        ResponseWrapper<Exam> response = new ResponseWrapper<>();
        response.setCode(200);
        response.setStatus(HttpStatus.OK);
        response.setData(updatedExam);
        return response;
    }


    @Override
    public PaginatedResponse<Exam> getExams(int page, int limit) {
        PageRequest pageRequest = PageRequest.of(page, limit);
        Page<Exam> exams = examRepository.findAll(pageRequest);
        PaginatedResponse<Exam> response = new PaginatedResponse<>();
        response.setCurrentPage(exams.getNumber());
        response.setPageSize(exams.getSize());
        response.setTotalElements(exams.getTotalElements());
        response.setTotalPages(exams.getTotalPages());
        response.setData(exams.getContent());
        return response;
    }


    @Override
    public ResponseWrapper<Exam> createExam(ExamDto req) {
        List<Exam> existingExams = examRepository.findByExamName(req.getExamName());
        if (!existingExams.isEmpty()) {
            throw new HttpConflict("Exam already exists with this name");
        }
        Exam exam = modelMapper.map(req, Exam.class);
        Exam addExam = examRepository.save(exam);
        examSessionService.createDefaultSessionsForExam(addExam);
        ResponseWrapper<Exam> response = new ResponseWrapper<>();
        response.setCode(201);
        response.setStatus(HttpStatus.CREATED);
        response.setData(addExam);
        return response;
    }

    @Override
    public MessageResponse deleteExam(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new HttpNotFound("Exam not found with id: " + id));
        examRepository.delete(exam);
        MessageResponse response = new MessageResponse();
        response.setMessage("Exam deleted successfully");
        return response;
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new HttpNotFound("Exam not found with id: " + id));
    }
}
