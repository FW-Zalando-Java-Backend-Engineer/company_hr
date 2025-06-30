package org.example.companyhr.sql;

/**
 * This enum documents the advanced SQL quires we want to use.
 * It serves as "catalog" of SQL concepts.
 * */
public enum EmployeeQuery {

    /**
      Basic SELECT all employees.
      */
    SELECT_ALL("SELECT * FROM employees"),
    /**
     * Find employees with salary above the threshold.
     * */
    HIGH_SALARY("SELECT * FROM employees WHERE salary > ?1"),

    // ?1: is a placeholder for the value we provide later (called a bind parameter).
    // ?1 is not literal SQL. however, it is a parameter marker used in JPA.
    // e.g. ?1 = 50000. then the query becomes: SELECT * FROM employees WHERE salary > 50000

    /**
     * Average salary by department using GROUP BY.
     * */
    AVG_SALARY_BY_DEPARTMENT("SELECT department, AVG(salary) as avg_salary FROM employees GROUP BY department"),

    /**
     * Employees earning above department average using correlated subquery.
     * */
    EMPLOYEES_ABOVE_DEPARTMENT_AVG("SELECT * FROM employees e WHERE salary > (SELECT AVG(salary) FROM employees WHERE department = e.department)"),


    /**
     * Employee ranking by salary within the department using window functions.
     */

    EMPLOYEE_RANKING("SELECT id, name, department, salary, RANK() OVER (PARTITION BY department ORDER BY salary DESC) AS dept_rank FROM employees");
    /**
     * ✅ What is a window function?
     * A window function computes a value across a set of rows related to the current row.
     *--
     * ✅ Unlike GROUP BY, it doesn't reduce the number of rows.
     * ✅ It adds extra info (like rank, average, sum) to each row.
     * --
     * RANK() is a window function: It assigns a ranking number '1 for highest', '2 for next highest', ...etc.
     * OVER (...) defines how to calculate the rank.
     * PARTITION BY department: Divide the table into groups based on department.
     * ORDER BY salary DESC: Within each department, sort employees by salary, highest first.
     * DESC: descending order.
     * The result set will include a column called dept_rank.
     * */

    // Field to Hold the SQL String
    private final String sql;

    EmployeeQuery(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

}
