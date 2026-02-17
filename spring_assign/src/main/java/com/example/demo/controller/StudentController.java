package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.db2.entity.Student;
import com.example.demo.db2.repo.StudentPost;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentPost repo;

    @GetMapping
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

  
    
 
    @PostMapping
    public String addStudent(@RequestBody Student s) {

        if (repo.existsById(s.getReg_no()))
            return "Student already exists";

        repo.save(s);
        return "Student inserted successfully";
    }

   
    @GetMapping("/school")
    public List<Student> getBySchool(@RequestParam String name) {
        return repo.findBySchoolIgnoreCase(name);
    }

    @GetMapping("/count")
    public long countBySchool(@RequestParam String name) {
        return repo.countBySchool(name);
    }

    @GetMapping("/school/standard/count")
    public long countByStandard(@RequestParam("class") int standard) {
        return repo.countByStandard(standard);
    }


    @GetMapping("/result")
    public List<Student> getResult(@RequestParam boolean pass) {
        return repo.findByResult(pass);
    }

   
    @GetMapping("/strength")
    public long getStrength(@RequestParam String gender,
                            @RequestParam int standard) {
        return repo.countByGenderAndStandard(gender, standard);
    }

   @GetMapping("/{regNo:\\d+}")
public Student getStudent(@PathVariable int regNo) {
    return repo.findById(regNo).orElse(null);
}

@PutMapping("/{regNo:\\d+}")
public String updateStudent(@PathVariable int regNo,
                            @RequestBody Student s) {
    if (!repo.existsById(regNo))
        return "Student not found";

    if (s.getReg_no() != regNo)
        return "Registration number mismatch";

    repo.save(s);
    return "Student updated successfully";
}

@DeleteMapping("/{regNo:\\d+}")
public String deleteStudent(@PathVariable int regNo) {
    if (!repo.existsById(regNo))
        return "Student not found";

    repo.deleteById(regNo);
    return "Student deleted successfully";
}

@PatchMapping("/{regNo:\\d+}")
public String partialUpdate(@PathVariable int regNo,
                            @RequestBody Student updatedData) {
    Optional<Student> optionalStudent = repo.findById(regNo);

    if (optionalStudent.isEmpty())
        return "Student not found";

    Student existing = optionalStudent.get();

    if (updatedData.getName() != null)
        existing.setName(updatedData.getName());

    if (updatedData.getSchool() != null)
        existing.setSchool(updatedData.getSchool());

    if (updatedData.getGender() != null)
        existing.setGender(updatedData.getGender());

    if (updatedData.getStd() != 0)
        existing.setStd(updatedData.getStd());

    if (updatedData.getPercentage() != 0)
        existing.setPercentage(updatedData.getPercentage());

    repo.save(existing);

    return "Student partially updated";
}

}
