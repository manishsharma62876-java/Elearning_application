package com.elearning.mapper;


import org.springframework.stereotype.Component;

import com.elearning.dto.request.CourseRequest;
import com.elearning.dto.response.CourseResponse;
import com.elearning.entity.Course;



@Component
public class CourseMapper {


    // DTO Request --> Entity

    public Course toEntity(CourseRequest request){


        Course course = new Course();


        course.setTitle(request.getTitle());

        course.setDescription(
                request.getDescription()
        );

        course.setCategory(
                request.getCategory()
        );

        course.setFees(
                request.getFees()
        );

        course.setDuration(
                request.getDuration()
        );


        return course;

    }





    // Entity --> Response DTO

    public CourseResponse toResponse(Course course){


        CourseResponse response =
                new CourseResponse();


        response.setId(course.getId());

        response.setTitle(
                course.getTitle()
        );

        response.setDescription(
                course.getDescription()
        );

        response.setCategory(
                course.getCategory()
        );

        response.setFees(
                course.getFees()
        );

        response.setDuration(
                course.getDuration()
        );


        return response;

    }



}