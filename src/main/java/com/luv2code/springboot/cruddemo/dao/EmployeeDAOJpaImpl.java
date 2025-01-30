package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    private EntityManager em;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Employee> findAll() {
        //create query
        TypedQuery<Employee> Query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        //execute query
        List<Employee> employees = Query.getResultList();
        //return results

        return employees;

    }

    @Override
    public Employee findById(int id) {
        Employee employee = em.find(Employee.class, id);
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        Employee savedEmployee = em.merge(employee);

        return savedEmployee;
    }

    @Override
    public void delete(int id) {
        em.remove(em.find(Employee.class, id));
    }
}
