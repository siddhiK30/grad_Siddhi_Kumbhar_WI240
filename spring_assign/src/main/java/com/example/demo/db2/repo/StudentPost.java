package com.example.demo.db2.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db2.entity.Student;
@Repository
public interface StudentPost extends JpaRepository<Student, Integer> {

}