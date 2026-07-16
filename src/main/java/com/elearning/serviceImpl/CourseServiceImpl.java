package com.elearning.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.dto.request.CourseRequest;
import com.elearning.dto.response.CourseResponse;
import com.elearning.entity.Course;
import com.elearning.exception.CourseAlreadyExistException;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.mapper.CourseMapper;
import com.elearning.repository.CourseRepository;
import com.elearning.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

	private final CourseRepository courseRepository;

	private final CourseMapper courseMapper;

// CREATE COURSE

	@Override
	public CourseResponse addCourse(CourseRequest request) {

		// Business Logic

		boolean exists = courseRepository.existsByTitle(request.getTitle());

		if (exists) {

			throw new CourseAlreadyExistException("Course already exists");

		}

		Course course = courseMapper.toEntity(request);

		Course savedCourse = courseRepository.save(course);

		return courseMapper.toResponse(savedCourse);

	}

// GET ALL COURSES

	@Override
	public List<CourseResponse> getAllCourses() {

		return courseRepository.findAll()

				.stream()

				.map(courseMapper::toResponse)

				.toList();

	}

// GET SINGLE COURSE

	@Override
	public CourseResponse getCourseById(Long id) {

		Course course = courseRepository.findById(id)

				.orElseThrow(() -> new ResourceNotFoundException("Course not found"));

		return courseMapper.toResponse(course);

	}

// UPDATE COURSE

	@Override
	public CourseResponse updateCourse(Long id, CourseRequest request) {

		Course course = courseRepository.findById(id)

				.orElseThrow(() -> new ResourceNotFoundException("Course not found"));

		course.setTitle(request.getTitle());

		course.setDescription(request.getDescription());

		course.setCategory(request.getCategory());

		course.setFees(request.getFees());

		course.setDuration(request.getDuration());

		Course updated = courseRepository.save(course);

		return courseMapper.toResponse(updated);

	}

// DELETE COURSE

	@Override
	public String deleteCourse(Long id) {

		Course course = courseRepository.findById(id)

				.orElseThrow(() -> new ResourceNotFoundException("Course not found"));

		courseRepository.delete(course);

		return "Course deleted successfully";

	}

}