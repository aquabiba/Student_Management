package Learning.student_management.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner cLR(StudentRepository repository) {
        return args -> {

            Student ikhlass = new Student(
                    "ikhlass",
                    LocalDate.of(1995, Month.SEPTEMBER,25),
                    0,
                    "ikhlass12@gmail.com");

            Student younesse = new Student(
                    "younesse",
                    LocalDate.of(1992, Month.FEBRUARY,6),
                    0,
                    "younesse23@gmail.com");
            repository.saveAll(
                    List.of(ikhlass, younesse));

        };
    }
}
