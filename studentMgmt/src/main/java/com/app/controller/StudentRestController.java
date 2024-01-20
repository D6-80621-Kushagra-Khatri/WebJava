package com.app.controller;

import com.app.dto.ApiResponse;
import com.app.dto.StudentDto;
import com.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    StudentService service;

    @PostMapping("/add/{courseId}")
    public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto, @PathVariable Long courseId)
    {
        ApiResponse apiResponse = service.addStudent(studentDto, courseId);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
