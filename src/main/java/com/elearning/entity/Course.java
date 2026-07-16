package com.elearning.entity;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="courses")
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    private String description;


    private String category;


    private Double fees;


    private String duration;


    private LocalDateTime createdAt;



    @PrePersist
    public void createdDate(){

        this.createdAt = LocalDateTime.now();

    }

}