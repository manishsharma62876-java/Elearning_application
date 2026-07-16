package com.elearning.dto.response;


import lombok.Data;


@Data
public class CourseResponse {


    private Long id;

    private String title;

    private String description;

    private String category;

    private Double fees;

    private String duration;


}