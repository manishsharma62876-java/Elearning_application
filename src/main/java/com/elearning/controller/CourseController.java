package com.elearning.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.dto.request.CourseRequest;
import com.elearning.dto.response.CourseResponse;
import com.elearning.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseController {

	private final CourseService courseService;

	// ADMIN CREATE COURSE

	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CourseResponse> addCourse(@RequestBody CourseRequest request) {

		return ResponseEntity.ok(courseService.addCourse(request));

	}

	// STUDENT VIEW ALL COURSES

//	@GetMapping
//	@PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
//	public ResponseEntity<List<CourseResponse>> getAllCourses() {
//
//		return ResponseEntity.ok(courseService.getAllCourses());
//
//	}
	// STUDENT VIEW ALL COURSES

	@GetMapping
	public ResponseEntity<List<CourseResponse>> getAllCourses() {

	    return ResponseEntity.ok(courseService.getAllCourses());

	}

	// VIEW COURSE DETAILS

//	@GetMapping("/{id}")
//	@PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
//	public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
//
//		return ResponseEntity.ok(courseService.getCourseById(id));
//
//	}

	// VIEW COURSE DETAILS

	@GetMapping("/{id}")
	public ResponseEntity<CourseResponse> getCourseById(
	        @PathVariable Long id) {

	    return ResponseEntity.ok(
	        courseService.getCourseById(id)
	    );

	}
	// ADMIN UPDATE COURSE

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id, @RequestBody CourseRequest request) {

		return ResponseEntity.ok(courseService.updateCourse(id, request));

	}

	// ADMIN DELETE COURSE

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCourse(@PathVariable Long id) {

		return ResponseEntity.ok(courseService.deleteCourse(id));

	}

}
