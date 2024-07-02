package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student student1 = new Student(
                    "Foster",
                    "fosteranopa@gmail.com",
                    LocalDate.of(2001, Month.MAY, 20));
            Student student2 = new Student(
                    "Felix",
                    "felixanopa@gmail.com",
                    LocalDate.of(2009, Month.NOVEMBER, 9));
            repository.saveAll(List.of(student1, student2));
        };
    }
}
