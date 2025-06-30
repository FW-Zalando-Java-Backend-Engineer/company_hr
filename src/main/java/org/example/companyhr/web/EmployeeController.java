package org.example.companyhr.web;

import org.example.companyhr.model.Employee;
import org.example.companyhr.repo.EmployeeRepository;
import org.example.companyhr.repo.EmployeeRepositoryCustom;
import org.example.companyhr.sql.EmployeeQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing Employee resources.
 * Demonstrates calling advanced SQL queries via clean REST endpoints.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repo;
    private final EmployeeRepositoryCustom repoCustom;

    /**
     * Constructor-based injection.
     * Spring will automatically wire the repositories.
     */
    public EmployeeController(EmployeeRepository repo, EmployeeRepositoryCustom repoCustom) {
        this.repo = repo;
        this.repoCustom = repoCustom;
    }

    /**
     * Retrieve all employees.
     * Example: GET /employees
     */
    @GetMapping
    public Iterable<Employee> getAll() {
        return repo.findAll();
    }

    /**
     * Add a new employee.
     * Example: POST /employees with JSON body
     */
    @PostMapping
    public Employee add(@RequestBody Employee e) {
        return repo.save(e);
    }

    /**
     * Query: Employees with salary above a threshold.
     * Example: GET /employees/high-salary?threshold=50000
     *
     * Note:
     * - Demonstrates WHERE condition with parameter binding.
     */
    @GetMapping("/high-salary")
    public List<Employee> highSalary(@RequestParam double threshold) {
        // Defensive logging or validation can be added here
        return repoCustom.executeEmployeeQuery(EmployeeQuery.HIGH_SALARY, threshold);
    }

    /**
     * Query: Average salary by department.
     * Example: GET /employees/avg-salary
     *
     * Note:
     * - Demonstrates GROUP BY with aggregation.
     * - Returns Object[] per row: [department, avg_salary]
     */
    @GetMapping("/avg-salary")
    public List<Object[]> avgSalaryByDepartment() {
        return repoCustom.executeObjectArrayQuery(EmployeeQuery.AVG_SALARY_BY_DEPARTMENT);
    }

    /**
     * Query: Employees earning above department average.
     * Example: GET /employees/above-avg
     *
     * Note:
     * - Demonstrates correlated subquery.
     */
    @GetMapping("/above-avg")
    public List<Employee> aboveAvg() {
        return repoCustom.executeEmployeeQuery(EmployeeQuery.EMPLOYEES_ABOVE_DEPARTMENT_AVG);
    }

    /**
     * Query: Employee salary ranking per department.
     * Example: GET /employees/ranking
     *
     * Note:
     * - Demonstrates window functions (RANK() OVER ... PARTITION BY).
     * - Returns Object[] per row: [id, name, department, salary, dept_rank]
     */
    @GetMapping("/ranking")
    public List<Object[]> ranking() {
        return repoCustom.executeObjectArrayQuery(EmployeeQuery.EMPLOYEE_RANKING);
    }
}
