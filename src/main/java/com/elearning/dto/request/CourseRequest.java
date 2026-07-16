package com.elearning.dto.request;


import lombok.Data;


@Data
public class CourseRequest {


    private String title;


    private String description;


    private String category;


    private Double fees;


    private String duration;


}