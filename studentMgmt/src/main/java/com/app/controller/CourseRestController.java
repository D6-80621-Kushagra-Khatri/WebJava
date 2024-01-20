package com.app.controller;

import com.app.dto.ApiResponse;
import com.app.dto.CourseDto;
import com.app.dto.CourseToUpdateDto;
import com.app.dto.StudentDto;
import com.app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/course")
@Validated
public class CourseRestController {
    @Autowired
    CourseService courseService;
    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid CourseDto courseDto){
        ApiResponse apiResponse = courseService.addCourse(courseDto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<?> updateCourse(@RequestBody @Valid CourseToUpdateDto courseToUpdateDto, @PathVariable Long courseId){
        ApiResponse apiResponse = courseService.updateCourse(courseToUpdateDto,courseId);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    @GetMapping("/list")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
    @GetMapping("/students/{courseName}")
    public ResponseEntity<List<StudentDto>> studentOfCourse(@PathVariable @NotNull String courseName)
    {
        return ResponseEntity.ok(courseService.studentsOfCourse(courseName));
    }
}
