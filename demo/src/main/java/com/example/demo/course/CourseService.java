package com.example.demo.course;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public void addNewCourse(Course course) {
        Optional<Course> CourseOptional = courseRepository.findCourseByCode(course.getCourseCode());
        if (CourseOptional.isPresent()) {
            throw new IllegalStateException("Course code already exists");
        }
        courseRepository.save(course);
    }

    public void deleteCourse(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            courseRepository.deleteById(courseId);
        } else {
            throw new IllegalStateException("No course with id " + courseId + " found");
        }
    }

    @Transactional
    public void updateCourse(Long courseId, String courseCode, String courseName) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("No course with id " + courseId));

        if (courseName != null && courseName.length() > 0 &&
                !Objects.equals(courseName, course.getCourseName())) {
            course.setCourseName(courseName);
        }

        if (courseCode != null && courseCode.length() > 0 &&
                !Objects.equals(courseCode, course.getCourseCode())) {
            Optional<Course> courseOptional = courseRepository.findCourseByCode(courseCode);
            if (courseOptional.isPresent()) {
                throw new IllegalStateException("Course code " + courseCode + " already exists");
            }
            course.setCourseCode(courseCode);
        }
    }

}
