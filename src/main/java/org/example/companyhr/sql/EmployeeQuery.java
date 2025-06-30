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
    EMPLOYEES_ABOVE_DEPARTMENT_AVG("SELECT * FROM employees e" +
            "WHERE salary > " +
            "(SELECT AVG(salary) FROM employees WHERE department = e.department");

    // Field to Hold the SQL String
    private final String sql;

    EmployeeQuery(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

}
