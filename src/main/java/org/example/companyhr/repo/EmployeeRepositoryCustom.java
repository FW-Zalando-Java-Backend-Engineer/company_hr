package org.example.companyhr.repo;

import org.example.companyhr.model.Employee;
import org.example.companyhr.sql.EmployeeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Custom interface to execute advanced SQL queries dynamically.
 */
@Repository
public interface EmployeeRepositoryCustom {
    /**
     * Executes a native SQL query returning a list of Employee entities.
     *
     * @param query  the EmployeeQuery enum
     * @param params optional query parameters
     * @return list of Employee objects
     */
    List<Employee>  executeEmployeeQuery(EmployeeQuery query, Object...params);
    // In our case: EmployeeQuery.HIGH_SALARY, 50000
    // params[0] = 50000


    /**
     * Executes a native SQL query returning Object arrays (e.g., for aggregation).
     *
     * @param query  the EmployeeQuery enum
     * @param params optional query parameters
     * @return list of Object arrays
     */
    List<Object[]> executeObjectArrayQuery(EmployeeQuery query, Object...params);

    // department  |  avg_salary
    //   HR             45000
    //   IT             65000

    // The java result would be:
    // result.get(0) -> Object[] [ {"HR", 45000},  {"IT", 65000} ]
}
