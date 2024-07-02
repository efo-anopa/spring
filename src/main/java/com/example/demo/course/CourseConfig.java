package com.example.demo.course;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseConfig {

    @Bean
    CommandLineRunner commandLineRunner1(CourseRepository repository) {
        return args -> {
            Course course1 = new Course(
                    "SENG301", "Numerical Methods");
            Course course2 = new Course(
                    "CPEN 204", "Data Structures and Algorithms");
            repository.saveAll(List.of(course1, course2));
        };
    }
}
