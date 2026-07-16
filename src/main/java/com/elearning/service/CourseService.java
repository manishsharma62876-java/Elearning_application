package com.elearning.service;


import java.util.List;

import com.elearning.dto.request.CourseRequest;
import com.elearning.dto.response.CourseResponse;


public interface CourseService {


    // Admin
    CourseResponse addCourse(
            CourseRequest request);



    CourseResponse updateCourse(
            Long id,
            CourseRequest request);



    String deleteCourse(Long id);



    // Student/Admin

    List<CourseResponse> getAllCourses();



    CourseResponse getCourseById(Long id);


}