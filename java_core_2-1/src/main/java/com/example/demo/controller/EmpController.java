package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.dao.EmpDAO;
import com.example.demo.entity.Employee;
import java.util.List;
@Controller
public class EmpController {

    @Autowired
    EmpDAO dao;

    // When user opens localhost:8181/
    @GetMapping("/")
    public String empManagement() {
        return "index";   // ❗ NOT index.jsp
    }

    @RequestMapping("/addEmp")
    public String addEmployee(Employee e) {
    	
    	if(dao.existsById(e.getEid()))
    		System.out.println("Alreday exist");
    	else
    		dao.save(e);
        return "index";   // ❗ NOT index.jsp
    }
    @RequestMapping("/searchEmp")
    @ResponseBody
    public String searchEmployee(int eid) {
      return   dao.findById(eid).orElse(new Employee()).toString();
        // ❗ NOT index.jsp
    }
    @RequestMapping("/updEmp")
    public String updateEmployee(Employee e) {
        dao.save(e);
        return "index";   // ❗ NOT index.jsp
    }
    @RequestMapping("/delEmp")
    @ResponseBody
    public String deleteEmployee(int eid) {
    	if(dao.existsById(eid))
    		dao.deleteById(eid);
    	else
    		System.out.println("Not exist");
    		
        return "Deleted";   // ❗ NOT index.jsp
    }
    
    @RequestMapping("/Employ")
    @ResponseBody
    public Iterable<Employee> findAll() {
       return  dao.findAll();
      
       // ❗ NOT index.jsp
    }
    @RequestMapping("/desgEmp")
    @ResponseBody
    public List<Employee> byDesg(String deisgnation) {
        return dao.findByDesignation(deisgnation);
    }
    @RequestMapping("/byAge")
    @ResponseBody
    public List<Employee> byDesg(int age) {
        return dao.findByAgeGreaterThan(age);
    }

    // @RequestMapping("/desgSort")
    // @ResponseBody
    // public List<Employee> byDesgSort(String deisgnation) {
    //     return dao.myOwnQuery(deisgnation);
    // }
     @RequestMapping("/desgSort")
    @ResponseBody
    public List<Employee> byDesgSort(String deisgnation) {
        return dao.myOwnQuery(deisgnation);
    }
}
