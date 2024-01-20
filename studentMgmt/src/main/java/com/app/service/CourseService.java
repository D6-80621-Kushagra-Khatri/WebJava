package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.CourseDto;
import com.app.dto.CourseToUpdateDto;
import com.app.dto.StudentDto;
import java.util.List;

public interface CourseService {
    public ApiResponse addCourse(CourseDto courseDto);
    public ApiResponse updateCourse(CourseToUpdateDto courseToUpdateDto, Long courseId);
    public List<CourseDto> getAllCourses();
    public List<StudentDto> studentsOfCourse(String courseName);
}
