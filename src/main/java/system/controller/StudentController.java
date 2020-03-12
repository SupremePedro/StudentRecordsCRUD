package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system.model.Student;
import system.service.StudentServiceImpl;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentsService;


    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        System.out.println("getAll");
        return ResponseEntity.ok(studentsService.getStudents());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getOne(@PathVariable(value = "id")Integer id) {
        return ResponseEntity.ok(studentsService.getStudent(id));
    }

    @PostMapping("/students")
    public void create(@RequestBody Student student) {
        System.out.println("create student");
        studentsService.addStudent(student);
    }

    @PutMapping("/students")
    public void update(@Valid @RequestBody Student student) {
        studentsService.updateStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable(value = "id") Integer id) {
        studentsService.deleteStudent(id);
    }
}
