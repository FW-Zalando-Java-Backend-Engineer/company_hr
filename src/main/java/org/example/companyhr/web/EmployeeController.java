package org.example.companyhr.web;


import org.example.companyhr.model.Employee;
import org.example.companyhr.repo.EmployeeRepository;
import org.example.companyhr.repo.EmployeeRepositoryCustom;
import org.example.companyhr.sql.EmployeeQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository repo;
    private final EmployeeRepositoryCustom repoCustom;


    public EmployeeController(EmployeeRepository repo, EmployeeRepositoryCustom  repoCustom) {
        this.repo = repo;
        this.repoCustom = repoCustom;
    }

    @GetMapping
    public Iterable<Employee> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Employee add(@RequestBody Employee e) {
        return repo.save(e);
    }

    @GetMapping("/high-salary") // ... /employees/high-salary?threshold=50000
    public List<Employee> highSalary(@RequestParam double threshold){
        return repoCustom.executeEmployeeQuery(EmployeeQuery.HIGH_SALARY, threshold);
    }

    @GetMapping("/avg-salary")
    public List<Object[]> avgSalaryByDepartment() {
        return repoCustom.executeObjectArrayQuery(EmployeeQuery.AVG_SALARY_BY_DEPARTMENT);
    }

    @GetMapping("/above-avg")
    public List<Employee> aboveAvg() {
        return repoCustom.executeEmployeeQuery(EmployeeQuery.EMPLOYEES_ABOVE_DEPARTMENT_AVG);
    }

    @GetMapping("/ranking")
    public List<Object[]> ranking() {
        return repoCustom.executeObjectArrayQuery(EmployeeQuery.EMPLOYEE_RANKING);
    }

}
