package com.app.service;

import com.app.dao.CourseDao;
import com.app.dao.StudentDao;
import com.app.dto.ApiResponse;
import com.app.dto.StudentDto;
import com.app.entities.Course;
import com.app.entities.Student;
import com.app.exception.ResourceNotFound;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentDao studentDao;
    @Autowired
    CourseDao courseDao;

    @Autowired
    ModelMapper mapper;
    @Override
    public ApiResponse addStudent(StudentDto studentDto, Long courseId) {
        Course course = courseDao.findById(courseId).orElseThrow(()->new ResourceNotFound("Could not get course! Invalid Course Id!"));
        course.addStudent(mapper.map(studentDto, Student.class));
        return new ApiResponse(201, "student added!!");
    }
}
