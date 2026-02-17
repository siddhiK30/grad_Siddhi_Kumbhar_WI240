package com.example.demo.controller;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.db1.entity.Student;
import com.example.demo.db1.repo.StudentDAO;
import com.example.demo.db2.repo.StudentPost;

@Controller
public class StudentController {

    @Autowired
    StudentDAO dao;

    @Autowired
    StudentPost pos;

    @GetMapping("/")
    public String studentManagement() {
        return "home.html";
    }
    @Transactional
    @RequestMapping("/addStu")
    @ResponseBody
    public String addStudent(Student s) {

        if (dao.existsById(s.getRollid())) {
            return "Already Exists";
        } else {
            dao.save(s);

            com.example.demo.db2.entity.Student s2 =
                    new com.example.demo.db2.entity.Student();

            s2.setRollid(s.getRollid());
            s2.setName(s.getName());
            s2.setStd(s.getStd());;
            s2.setSchool(s.getSchool());
          

            pos.save(s2);
        }

        return "Saved Successfully";
    }
}
