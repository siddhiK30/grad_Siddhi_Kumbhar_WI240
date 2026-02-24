package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.entity.Employee;
import java.util.List;

public interface EmpDAO extends JpaRepository<Employee, Integer> {

    // Correct method name
    List<Employee> findByDesignation(String deisgnation);

    List<Employee> findByAgeGreaterThan(int age);

    // Correct JPQL query
    @Query("SELECT e FROM Employee e WHERE e.deisgnation = ?1 ORDER BY e.age")
    List<Employee> myOwnQuery(String deisgnation);
}
