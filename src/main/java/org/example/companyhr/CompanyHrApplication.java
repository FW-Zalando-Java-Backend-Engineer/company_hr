package org.example.companyhr;

import org.example.companyhr.model.Employee;
import org.example.companyhr.repo.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

/**
 * Main Spring Boot application for Company HR SQL Demo.
 */
@SpringBootApplication
public class CompanyHrApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompanyHrApplication.class, args);
    }

    /**
     * Seeds the database with sample employees at startup.
     */
    @Bean
    CommandLineRunner init(EmployeeRepository repo) {
        return args -> {
            repo.save(new Employee("Alice Smith", "HR", 50000.0));
            repo.save(new Employee("Bob Johnson", "Engineering", 70000.0));
            repo.save(new Employee("Charlie Brown", "Engineering", 80000.0));
            repo.save(new Employee("Diana Prince", "HR", 55000.0));
            repo.save(new Employee("Eve Adams", "Sales", 60000.0));
        };
    }

}
