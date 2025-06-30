package org.example.companyhr.repo;



import org.example.companyhr.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Employee entity.
 * Demo of using advanced SQL queries using nativeQuery.
 * */
@Repository
public interface EmployeeRepository extends  CrudRepository<Employee, Long> {



}
