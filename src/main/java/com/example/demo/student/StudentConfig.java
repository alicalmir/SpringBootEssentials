package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.util.Calendar.AUGUST;


@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){

        return  args->{
            Student almir = new Student(
                    1L,
                    "Almir",
                    "almir@gmail.com",
                    LocalDate.of(2000, AUGUST,25)

            );
            Student alex = new Student(
                    2L,
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, AUGUST,25)

            );
            repository.saveAll(
                    List.of(almir,alex)
            );
        };
    }
}
