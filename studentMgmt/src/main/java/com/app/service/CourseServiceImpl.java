package com.app.service;

import com.app.dao.CourseDao;
import com.app.dto.ApiResponse;
import com.app.dto.CourseDto;
import com.app.dto.CourseToUpdateDto;
import com.app.dto.StudentDto;
import com.app.entities.Course;
import com.app.entities.Student;
import com.app.exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseDao courseDao;

    @Autowired
    ModelMapper mapper;
    @Override
    public ApiResponse addCourse(CourseDto courseDto) {
        if(courseDto.getStartDate().isAfter(courseDto.getEndDate()))
            return new ApiResponse(400, "start date cannot be after end date");
        Course course = mapper.map(courseDto, Course.class);
        course.setGradeToPass('B');
        courseDao.save(course);
        return (new ApiResponse(201,"course added successfully!!"));
    }

    @Override
    public ApiResponse updateCourse(CourseToUpdateDto courseToUpdateDto, Long courseId) {
        if(courseToUpdateDto.getStartDate().isAfter(courseToUpdateDto.getEndDate()))
            return new ApiResponse(400,"start date cannot be after end date");
        Course course = courseDao.findById(courseId).orElseThrow(()->new ResourceNotFound("Course not found!!"));
        course.setFees(courseToUpdateDto.getFees());
        course.setStartDate(courseToUpdateDto.getStartDate());
        course.setEndDate(courseToUpdateDto.getEndDate());
        return new ApiResponse(201, "course updated successfully!");
    }


    @Override
    public List<CourseDto> getAllCourses() {
        return courseDao.findAll().stream().map(c->mapper.map(c, CourseDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> studentsOfCourse(String courseName) {
       Course course = courseDao.findAll().stream().filter(c->c.getName().equals(courseName)).findFirst().orElseThrow(()->new ResourceNotFound("course does not exist! :<"));
        List<Student> studentList = course.getStudentsEnrolled();
        return studentList.stream().map(s->mapper.map(s,StudentDto.class)).collect(Collectors.toList());
    }
}
