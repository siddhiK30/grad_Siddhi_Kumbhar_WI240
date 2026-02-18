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

    private List<Student> studentsList;
    @GetMapping
    public List<Student> getAllStudents() {
        studentsList =  repo.findAll();
        return studentsList;
    }

  
    
 
    @PostMapping
    public String addStudent(@RequestBody Student s) {

        if (repo.existsById(s.getRegNo()))
            return "Student already exists";

        repo.save(s);
        studentsList =  repo.findAll();
        return "Student inserted successfully";
    }

//     @GetMapping("/school")
// public List<Student> getBySchool(@RequestParam String name) {
//     return repo.findBySchoolIgnoreCase(name);
// }

   
        @GetMapping("/school")
    public List<Student> getBySchool(@RequestParam String name) {
        return studentsList.stream()
                .filter(s -> s.getSchool().equalsIgnoreCase(name))
                .toList();
    }

   

    // @GetMapping("/count")
    // public long countBySchool(@RequestParam String name) {
    //     return repo.countBySchool(name);
    // }

    
    @GetMapping("/count")
public long countBySchool(@RequestParam String name) {
    return studentsList.stream()
            .filter(s -> s.getSchool().equalsIgnoreCase(name))
            .count();
}


    // @GetMapping("/school/standard/count")
    // public long countByStandard(@RequestParam("class") int standard) {
    //     return repo.countByStandard(standard);
    // }

    @GetMapping("/school/standard/count")
public long countByStandard(@RequestParam("class") int standard) {
     return studentsList.stream()
        .filter(s -> s.getStd() == standard)
        .count();
}


    @GetMapping("/result")
    public List<Student> getResult(@RequestParam boolean pass) {
        return repo.findByResult(pass);
    }

   
    // @GetMapping("/strength")
    // public long getStrength(@RequestParam String gender,
    //                         @RequestParam int standard) {
    //     return repo.countByGenderAndStandard(gender, standard);
    // }

   @GetMapping("/strength")
public long getStrength(@RequestParam String gender,
                        @RequestParam int standard) {

    return studentsList.stream()
            .filter(s -> s.getStd() == standard &&
                         s.getGender().equalsIgnoreCase(gender))
            .count();
}

//    @GetMapping("/{regNo:\\d+}")
// public Student getStudent(@PathVariable int regNo) {
//     return repo.findById(regNo).orElse(null);
// }
@GetMapping("/{regNo:\\d+}")
public Student getStudent(@PathVariable int regNo) {

    return studentsList.stream()
            .filter(s -> s.getRegNo() == regNo)
            .findFirst()
            .orElse(null);
}


@PutMapping("/{regNo:\\d+}")
public String updateStudent(@PathVariable int regNo,
                            @RequestBody Student s) {
    if (!repo.existsById(regNo))
        return "Student not found";

    if (s.getRegNo() != regNo)
        return "Registration number mismatch";

    repo.save(s);
    studentsList =  repo.findAll();

    return "Student updated successfully";
}

@DeleteMapping("/{regNo:\\d+}")
public String deleteStudent(@PathVariable int regNo) {
    if (!repo.existsById(regNo))
        return "Student not found";

    repo.deleteById(regNo);
    studentsList =  repo.findAll();

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
    studentsList =  repo.findAll();


    return "Student partially updated";
}

}
