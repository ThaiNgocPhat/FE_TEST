package com.ra.base_spring_boot.controller;

import com.ra.base_spring_boot.dto.ResponseWrapper;
import com.ra.base_spring_boot.dto.req.UserAnswerDto;
import com.ra.base_spring_boot.dto.req.UserExamResultDto;
import com.ra.base_spring_boot.model.constants.SessionType;
import com.ra.base_spring_boot.services.IUserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserAnswerController {
    private final IUserAnswerService userAnswerService;

    @PostMapping("/submit-answers")
    public ResponseEntity<ResponseWrapper<String>> submitAnswers(@RequestBody List<UserAnswerDto> userAnswers) {
        ResponseWrapper<String> response = userAnswerService.submitAnswers(userAnswers);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/result")
    public ResponseEntity<ResponseWrapper<UserExamResultDto>> getUserExamResult(
            @RequestParam Long userId,
            @RequestParam Long examId,
            @RequestParam SessionType sessionType) {
        ResponseWrapper<UserExamResultDto> response = userAnswerService.getUserExamResult(userId, examId, sessionType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
