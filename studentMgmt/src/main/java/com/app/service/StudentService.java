package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.StudentDto;


public interface StudentService {
    public ApiResponse addStudent(StudentDto studentDto, Long courseId);
}
