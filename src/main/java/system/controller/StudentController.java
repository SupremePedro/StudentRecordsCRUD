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
    public Student getOne(@PathVariable(value = "id")Integer id) {
        return studentsService.getStudent(id);
    }

    @PostMapping("/students")
    public void create(@RequestBody Student student) {
        System.out.println("create student");
        studentsService.addStudent(student);
    }

    @PutMapping("/students/{id}")
    public void update(@PathVariable(value = "id")Integer id, @RequestBody Student student) {
        studentsService.updateStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable(value = "id") Integer id) {
        studentsService.deleteStudent(id);
    }
}
