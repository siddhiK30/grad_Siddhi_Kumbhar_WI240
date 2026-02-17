package com.example.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db1.entity.Student;
@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {

}