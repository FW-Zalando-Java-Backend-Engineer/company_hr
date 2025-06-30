package org.example.companyhr.repo;

import org.example.companyhr.model.Employee;
import org.example.companyhr.sql.EmployeeQuery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


/**
 * Implementation of EmployeeRepositoryCustom using EntityManager.
 * Executes dynamic native queries from EmployeeQuery enum.
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{

    private final EntityManager entityManager;

    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }



    @Override
    public List<Employee> executeEmployeeQuery(EmployeeQuery query, Object... params) {
        Query nativeQuery = entityManager.createNativeQuery(query.getSql(), Employee.class);
        setParameters(nativeQuery, params);
        return nativeQuery.getResultList();
    }


    @Override
    public List<Object[]> executeObjectArrayQuery(EmployeeQuery query, Object... params) {
        Query nativeQuery = entityManager.createNativeQuery(query.getSql());
        setParameters(nativeQuery, params);
        return nativeQuery.getResultList();

    }

    /**
     * Utility to bind parameters to the native query.
     */
    private void setParameters(Query nativeQuery, Object...params) {
        if (params != null){
            for (int i = 0; i < params.length; i++){
                nativeQuery.setParameter(i+1, params[i+1]);
            }
        }
    }

}
