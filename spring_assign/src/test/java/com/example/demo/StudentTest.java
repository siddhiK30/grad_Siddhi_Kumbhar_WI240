package com.example.demo;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import com.example.demo.controller.StudentController;
import com.example.demo.db2.entity.Student;
import com.example.demo.db2.repo.StudentPost;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentPost repo;

  
 
    @Test
    void testGetStudentFound() throws Exception {

        Student s = new Student();
        s.setRegNo(1);
        s.setName("Siddhi");
        s.setSchool("ABC");
        s.setStd(10);

        when(repo.findById(1)).thenReturn(Optional.of(s));

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Siddhi"));

        verify(repo).findById(1);
    }

 
    @Test
    void testGetStudentNotFound() throws Exception {

        when(repo.findById(2)).thenReturn(Optional.empty());

        mockMvc.perform(get("/students/2"))
                .andExpect(status().isNotFound());

        verify(repo).findById(2);
    }
}