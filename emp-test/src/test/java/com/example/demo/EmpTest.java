package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.EmployeeController;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Test
    void testEmployeeFound() throws Exception {

        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Siddhi");

        when(service.getEmployeeById(1L)).thenReturn(Optional.of(emp));

        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk());   // 200
    }

    @Test
    void testEmployeeNotFound() throws Exception {

        when(service.getEmployeeById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/employee/2"))
                .andExpect(status().isNotFound());  // 404
    }
}