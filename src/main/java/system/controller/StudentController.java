package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.model.Student;
import system.service.StudentServiceImpl;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentsService;


    @GetMapping("/students")
    public List<Student> getAllStudents() {
        System.out.println("getAll");
        return studentsService.getStudents();
    }

    @GetMapping("/students/{id}")
    public Student getOne(@PathVariable String id) {
        return studentsService.getStudent(Integer.parseInt(id));
    }
}
