package com.example.demo.db2.repo;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.demo.db2.entity.Student;
public interface StudentPost extends JpaRepository<Student, Integer> {

    List<Student> findBySchoolIgnoreCase(String school);


    @Query("SELECT COUNT(s) FROM Student s WHERE s.school = :name")
    long countBySchool(@Param("name") String name);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.std = :standard")
    long countByStandard(@Param("standard") int standard);

    @Query("SELECT s FROM Student s WHERE " +
           "(:pass = true AND s.percentage >= 40) OR " +
           "(:pass = false AND s.percentage < 40) " +
           "ORDER BY s.percentage DESC")
    List<Student> findByResult(@Param("pass") boolean pass);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.gender = :gender AND s.std = :standard")
    long countByGenderAndStandard(@Param("gender") String gender,
                                  @Param("standard") int standard);
}
