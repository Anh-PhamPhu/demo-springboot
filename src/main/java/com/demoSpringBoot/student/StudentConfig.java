package com.demoSpringBoot.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student s1 = new Student(
                    "Anh Pham Phu",
                    "anhpham.96.a@gmail.com",
                    LocalDate.of(1996, 9, 22)
            );
            Student s2 = new Student(
                    "Pham Anh",
                    "anhpham.96.a@gmail.com",
                    LocalDate.of(1996, 9, 22)
            );
            Student s3 = new Student(
                    "Pham",
                    "anhpham.96.a@gmail.com",
                    LocalDate.of(1996, 9, 22)
            );
            studentRepository.saveAll(
                    List.of(s1, s2, s3)
            );
        };
    }
}
